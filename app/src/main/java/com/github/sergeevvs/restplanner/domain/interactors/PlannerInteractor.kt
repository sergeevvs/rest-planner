package com.github.sergeevvs.restplanner.domain.interactors

import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.github.sergeevvs.restplanner.App
import com.github.sergeevvs.restplanner.data.Days
import com.github.sergeevvs.restplanner.data.NotificationWorker
import com.github.sergeevvs.restplanner.data.repositories.PreferencesRepository
import java.util.concurrent.TimeUnit

class PlannerInteractor(
    private val workManager: WorkManager,
    private val prefRepository: PreferencesRepository
) {

    var plannerActive
        get() = prefRepository.plannerActive
        set(value) {
            prefRepository.plannerActive = value
            Log.d(App.LOG_TAG, "Change planner active on $value")
        }

    var notificationPeriod
        get() = prefRepository.notificationPeriod
        set(value) {
            prefRepository.notificationPeriod = value
            Log.d(App.LOG_TAG, "New notification period - $value minutes")
        }

    fun isDayActive(day: Days) = when (day) {
        Days.MONDAY -> prefRepository.monday
        Days.TUESDAY -> prefRepository.tuesday
        Days.WEDNESDAY -> prefRepository.wednesday
        Days.THURSDAY -> prefRepository.thursday
        Days.FRIDAY -> prefRepository.friday
        Days.SATURDAY -> prefRepository.saturday
        Days.SUNDAY -> prefRepository.sunday
    }

    fun setDayActive(day: Days, value: Boolean) {
        when (day) {
            Days.MONDAY -> prefRepository.monday = value
            Days.TUESDAY -> prefRepository.tuesday = value
            Days.WEDNESDAY -> prefRepository.wednesday = value
            Days.THURSDAY -> prefRepository.thursday = value
            Days.FRIDAY -> prefRepository.friday = value
            Days.SATURDAY -> prefRepository.saturday = value
            Days.SUNDAY -> prefRepository.sunday = value
        }
        Log.d(App.LOG_TAG, "Set $day activating in $value")
    }

    // При изменении настроек апдейтим менеджер нотификаций
    fun updateNotificationManager() {
        if (plannerActive) {
            workManager.enqueue(
                OneTimeWorkRequestBuilder<NotificationWorker>()
                    .setInitialDelay(getNotificationTimeDiff(), TimeUnit.MILLISECONDS)
                    .addTag(REST_WORK_TAG)
                    .setInputData(getNotificationData())
                    .build()
            )
        } else {
            workManager.cancelAllWorkByTag(REST_WORK_TAG)
        }
    }

    private fun getNotificationTimeDiff() = 1L // FIXME: 16.04.2021

    private fun getNotificationData() = workDataOf(
        NOTIFICATION_PERIOD to prefRepository.notificationPeriod
    )

    companion object {
        const val REST_WORK_TAG = "rest_worker"
        const val NOTIFICATION_PERIOD = "notification_period"
    }
}