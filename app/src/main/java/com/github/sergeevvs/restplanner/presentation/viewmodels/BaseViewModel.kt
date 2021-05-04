package com.github.sergeevvs.restplanner.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.github.sergeevvs.restplanner.data.EXECUTE_NOTIFICATION
import com.github.sergeevvs.restplanner.data.LOG_TAG
import com.github.sergeevvs.restplanner.data.NotificationWorker
import com.github.sergeevvs.restplanner.data.REST_WORK_NAME
import com.github.sergeevvs.restplanner.data.repositories.PreferencesRepository
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var wm: WorkManager

    @Inject
    lateinit var prefRepository: PreferencesRepository

    // При изменении настроек апдейтим менеджер нотификаций
    protected fun updateNotificationManager() {
        if (prefRepository.plannerActive) {
            wm.enqueueUniqueWork(
                REST_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequestBuilder<NotificationWorker>()
                    .setInputData(
                        workDataOf(
                            EXECUTE_NOTIFICATION to false
                        )
                    )
                    .build()
            )
        } else {
            wm.cancelAllWork()
        }
        Log.d(LOG_TAG, "Notification manager updated.")
    }

    fun onFragmentStop() {
        updateNotificationManager()
    }
}