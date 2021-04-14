package com.github.sergeevvs.restplanner.presentation.viewmodels

import android.content.Context
import android.content.res.Resources
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.children
import androidx.lifecycle.ViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.github.sergeevvs.restplanner.R
import com.github.sergeevvs.restplanner.data.NotificationWorker
import com.github.sergeevvs.restplanner.data.Preferences
import java.util.concurrent.TimeUnit

class PlannerViewModel(
    private val preferences: Preferences
) : ViewModel() {

    private var workManager: WorkManager? = null

    var plannerActive: Boolean
        get() = preferences.active
        set(value) {
            preferences.active = value
        }

    var plannerTime: Int
        get() = preferences.time
        set(value) {
            preferences.time = value
        }

    var monday: Boolean
        get() = preferences.monday
        set(value) {
            preferences.monday = value
        }

    var tuesday: Boolean
        get() = preferences.tuesday
        set(value) {
            preferences.tuesday = value
        }

    var wednesday: Boolean
        get() = preferences.wednesday
        set(value) {
            preferences.wednesday = value
        }

    var thursday: Boolean
        get() = preferences.thursday
        set(value) {
            preferences.thursday = value
        }

    var friday: Boolean
        get() = preferences.friday
        set(value) {
            preferences.friday = value
        }

    var saturday: Boolean
        get() = preferences.saturday
        set(value) {
            preferences.saturday = value
        }

    var sunday: Boolean
        get() = preferences.sunday
        set(value) {
            preferences.sunday = value
        }

    // При изменении состояния planner active switch апдейтим менеджер нотификаций
    fun updateNotificationManager(appContext: Context) {
        workManager = WorkManager.getInstance(appContext)
        val notificationRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(
                plannerTime.toLong(), TimeUnit.MINUTES,
                0, TimeUnit.MINUTES
            ).build()
        if (plannerActive) {
            workManager?.enqueueUniquePeriodicWork(
                restWorkName,
                ExistingPeriodicWorkPolicy.KEEP,
                notificationRequest
            )
        } else {
            workManager?.cancelUniqueWork(restWorkName)
        }
    }

    fun saveNotificationPeriod(resources: Resources, radioGroup: RadioGroup) {
        for (rb in radioGroup.children)
            if ((rb as RadioButton).isChecked)
                plannerTime = getTimeByRadioButton(resources, rb)
    }

    private fun getTimeByRadioButton(resources: Resources, rb: RadioButton): Int {
        return when(rb.text) {
            resources.getString(R.string.every_15_minutes) -> 15
            resources.getString(R.string.every_30_minutes) -> 30
            resources.getString(R.string.every_45_minutes) -> 45
            resources.getString(R.string.every_60_minutes) -> 60
            resources.getString(R.string.every_90_minutes) -> 90

            else -> 0
        }
    }

    fun getRadioButtonByTime(): Int {
        return when(plannerTime) {
            15 -> R.id.every_15_minutes
            30 -> R.id.every_30_minutes
            45 -> R.id.every_45_minutes
            60 -> R.id.every_60_minutes
            90 -> R.id.every_90_minutes

            else -> 0
        }
    }

    companion object {

        const val restWorkName = "Rest worker"
    }
}