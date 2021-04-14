package com.github.sergeevvs.restplanner.data

import android.content.Context
import android.graphics.Color
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.github.sergeevvs.restplanner.R

class NotificationWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val builder =
            NotificationCompat.Builder(
                applicationContext,
                NotificationWorker::class.java.toString()
            )
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(applicationContext.getString(R.string.notification_title))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate(longArrayOf(0, 200, 200, 200))
                .setLights(Color.RED, 3000, 3000)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(notificationId, builder.build())
            Log.d(super.toString(), "Sent notification.")
        }

        return Result.success()
    }

    companion object {

        const val notificationId = 888
    }
}