package com.github.sergeevvs.restplanner

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import com.github.sergeevvs.restplanner.data.NotificationWorker
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @Test
    fun testNotificationWorker() {
        val request = PeriodicWorkRequestBuilder<NotificationWorker>(
            15, TimeUnit.MINUTES,
            0, TimeUnit.MINUTES
        ).build()
        val workManager = WorkManager.getInstance(context)
        val testDriver = WorkManagerTestInitHelper.getTestDriver(context)

        workManager.enqueue(request).result.get()
        Thread.sleep(5000)
        testDriver?.setPeriodDelayMet(request.id)
        val workInfo = workManager.getWorkInfoById(request.id).get()

        assertThat(workInfo.state, `is`(WorkInfo.State.ENQUEUED))
    }
}