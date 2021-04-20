package com.github.sergeevvs.restplanner.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.github.sergeevvs.restplanner.data.NotificationWorker
import com.github.sergeevvs.restplanner.data.repositories.PreferencesRepository
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var wm: WorkManager

    @Inject
    lateinit var prefRepository: PreferencesRepository

    // При изменении настроек апдейтим менеджер нотификаций
    fun updateNotificationManager() {
        if (prefRepository.plannerActive) {
            wm.enqueueUniqueWork(
                REST_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequestBuilder<NotificationWorker>()
                    .setInitialDelay(getNotificationTimeDiff(), TimeUnit.MILLISECONDS)
                    .setInputData(getNotificationData())
                    .build()
            )
        } else {
            wm.cancelAllWork()
        }
    }

    /**
     * Метод высчитывает время до следующей нотификации, учитывая дни и время активности планировщика,
     * обеденное время (если оно включено).
     * */
    private fun getNotificationTimeDiff(): Long {
        val period = prefRepository.notificationPeriod
        var result = period
        val currentTime = Calendar.getInstance().timeInMillis


        // test times
        val testStartTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 10)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.timeInMillis
        val testEndTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 20)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.timeInMillis

        if (currentTime > testStartTime && currentTime + period < testEndTime) {
            result = period - (currentTime % period)
//            if (currentTime.days)
        }

        return result
    }

    private fun getNotificationData() = workDataOf(
        NOTIFICATION_PERIOD to prefRepository.notificationPeriod
    )

    fun onFragmentStop() {
        updateNotificationManager()
    }

    companion object {
        const val REST_WORK_NAME = "rest_work_name"
        const val NOTIFICATION_PERIOD = "notification_period"
    }
}