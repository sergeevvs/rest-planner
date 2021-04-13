package com.github.sergeevvs.restplanner.presentation.viewmodels

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.lifecycle.ViewModel
import com.github.sergeevvs.restplanner.data.AlarmReceiver
import com.github.sergeevvs.restplanner.data.Preferences

class PlannerViewModel(
    private val preferences: Preferences
) : ViewModel() {

    private var alarmManager: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

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

    fun updateAlarmManager(context: Context) {
        if (plannerActive) {
            alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(context, 0, intent, 0)
            }

            /* Тестирование, для повторяющихся алармов нужно пользоваться методом setRepeating */
            alarmManager?.set(
                AlarmManager.RTC_WAKEUP,
                SystemClock.elapsedRealtime() + 10 * 1000,
                alarmIntent
            )
        }
    }
}