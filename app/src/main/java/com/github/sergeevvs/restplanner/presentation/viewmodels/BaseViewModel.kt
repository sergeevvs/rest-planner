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
     *
     * ЕСЛИ текущий день активен И
     * текущее время >= времени начала рабочего дня И
     * текущее время + период нотификации < времени конца рабочего дня
     * ТО -> возвращаем период нотификации, с учетом разницы с текущим временем
     * (Например сейчас 10:45, а запустить надо в 11:00, при периоде в 60 минут)
     * ИНАЧЕ -> высчитываем время старта слудующего дня
     * */
    private fun getNotificationTimeDiff(): Long {
        val calendar = Calendar.getInstance()
        val period = prefRepository.notificationPeriod
        val currentTime = calendar.timeInMillis


        // test times
        val testStartTime = calendar.apply {
            set(Calendar.HOUR_OF_DAY, 10)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.timeInMillis
        val testEndTime = calendar.apply {
            set(Calendar.HOUR_OF_DAY, 17)
            set(Calendar.MINUTE, 53)
            set(Calendar.SECOND, 0)
        }.timeInMillis


        return if (prefRepository.isDayActive(calendar[Calendar.DAY_OF_WEEK]) &&
            currentTime >= testStartTime &&
            currentTime + period < testEndTime
        ) {
            period - (currentTime % period)
        } else {
            (testStartTime - currentTime) + (24 * 60 * 60 * 1000)
        }
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