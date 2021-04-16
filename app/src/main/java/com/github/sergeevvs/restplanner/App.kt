package com.github.sergeevvs.restplanner

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.work.WorkManager
import com.github.sergeevvs.restplanner.data.repositories.PreferencesRepository
import com.github.sergeevvs.restplanner.domain.interactors.PlannerInteractor
import com.github.sergeevvs.restplanner.presentation.viewmodels.ViewModelFactory

class App : Application() {

    lateinit var viewModelFactory: ViewModelFactory
        private set

    override fun onCreate() {
        super.onCreate()

        val preferencesRepository = PreferencesRepository(this)
        val workManager = WorkManager.getInstance(this)
        val plannerInteractor = PlannerInteractor(workManager, preferencesRepository)
        viewModelFactory = ViewModelFactory(plannerInteractor)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val description = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                this.description = description
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 200, 200, 200)
                enableLights(true)
                lightColor = Color.RED
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "rest_planner_channel_id"
        const val LOG_TAG = "rest_planner_log"
    }
}