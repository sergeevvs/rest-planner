package com.github.sergeevvs.restplanner.data

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.github.sergeevvs.restplanner.App
import com.github.sergeevvs.restplanner.R
import com.github.sergeevvs.restplanner.presentation.MainActivity

class NotificationWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        with(NotificationManagerCompat.from(applicationContext)) {
            notify(NOTIFICATION_ID, createNotification().build())
        }
        Log.d(App.LOG_TAG, "Sent notification.")



        return Result.success()
    }

    private fun createNotification() =
        NotificationCompat.Builder(applicationContext, App.CHANNEL_ID)
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


    companion object {
        const val NOTIFICATION_ID = 888
    }
}