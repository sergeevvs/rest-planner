package com.github.sergeevvs.restplanner.data

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.github.sergeevvs.restplanner.R
import com.github.sergeevvs.restplanner.data.repositories.PreferencesRepository
import com.github.sergeevvs.restplanner.presentation.MainActivity
import java.util.*
import java.util.concurrent.TimeUnit

class NotificationWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

    private val wm = WorkManager.getInstance(applicationContext)
    private val prefs = PreferencesRepository(applicationContext)
    private val calendar = Calendar.getInstance()

    override fun doWork(): Result {

        if (inputData.getBoolean(EXECUTE_NOTIFICATION, false) &&
            prefs.isDayActive(calendar[Calendar.DAY_OF_WEEK])
        ) {
            with(NotificationManagerCompat.from(applicationContext)) {
                notify(NOTIFICATION_ID, createNotification().build())
            }
            Log.d(LOG_TAG, "Notification sent.")
        }

        wm.enqueueUniqueWork(
            REST_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequestBuilder<NotificationWorker>()
                .setInitialDelay(
                    getNotificationTimeDiff(),
                    TimeUnit.MILLISECONDS
                )
                .setInputData(
                    workDataOf(
                        EXECUTE_NOTIFICATION to true
                    )
                )
                .build()
        )
        Log.d(LOG_TAG, "New notification worker started.")

        return Result.success()
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
     * ИНАЧЕ -> высчитываем время старта слудующего дня + период нотификации
     * */
    private fun getNotificationTimeDiff(): Long {
        val period = prefs.timeToMillis(minute = prefs.notificationPeriod)
        val currentTime = calendar.timeInMillis

        calendar.apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        val startTime =
            calendar.timeInMillis + prefs.timeToMillis(prefs.startHour, prefs.startMinute)
        var endTime = calendar.timeInMillis + prefs.timeToMillis(prefs.endHour, prefs.endMinute)
        if (endTime < startTime) endTime += 24 * 60 * 60 * 1000

        val result = if (prefs.isDayActive(calendar[Calendar.DAY_OF_WEEK]) &&
            currentTime >= startTime &&
            currentTime + period < endTime
        ) period - ((currentTime - startTime) % period)
        else if (prefs.isDayActive(calendar[Calendar.DAY_OF_WEEK]) &&
            currentTime < startTime
        ) startTime - currentTime + period
        else startTime - currentTime + period + (24 * 60 * 60 * 1000)


        Log.d(
            LOG_TAG,
            "Notification delay = ${prefs.millisToTime(result)}, in millis = $result"
        )
        return result
    }

    private fun createNotification() =
        NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(applicationContext.resources.getString(R.string.notification_title))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(0, 200, 200, 200))
            .setLights(Color.RED, 3000, 3000)
            .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
            .setContentIntent(createPendingIntent())
            .setAutoCancel(true)

    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(applicationContext, MainActivity::class.java)
        return PendingIntent.getActivity(applicationContext, 0, intent, 0)
    }
}