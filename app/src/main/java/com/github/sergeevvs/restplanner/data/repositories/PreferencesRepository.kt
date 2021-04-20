package com.github.sergeevvs.restplanner.data.repositories

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.github.sergeevvs.restplanner.App
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepository @Inject constructor(@ApplicationContext appContext: Context) {

    private val preferences = appContext.getSharedPreferences(APP_SETTINGS, MODE_PRIVATE)

    init {
        preferences.edit().clear().apply()
    }

    var plannerActive: Boolean
        get() = preferences.getBoolean(PLANNER_ACTIVE, false)
        set(value) {
            preferences.edit().putBoolean(PLANNER_ACTIVE, value).apply()
        }

    var notificationPeriod: Long
        get() = preferences.getLong(NOTIFICATION_PERIOD, 0L)
        set(value) {
            preferences.edit().putLong(NOTIFICATION_PERIOD, value).apply()
        }

    var startTime: Long
        get() = preferences.getLong(START_TIME, 0L)
        set(value) {
            preferences.edit().putLong(START_TIME, value).apply()
        }

    var endTime: Long
        get() = preferences.getLong(END_TIME, 0L)
        set(value) {
            preferences.edit().putLong(END_TIME, value).apply()
        }

    fun isDayActive(day: Int) = preferences.getBoolean(day.toString(), false)

    fun setDayActive(day: Int, value: Boolean) {
        preferences.edit().putBoolean(day.toString(), value).apply()
        Log.d(App.LOG_TAG, "Set $day activating in $value")
    }

    companion object {
        const val APP_SETTINGS = "app_settings"
        const val PLANNER_ACTIVE = "planner_active"
        const val NOTIFICATION_PERIOD = "notification_period"
        const val START_TIME = "start_time"
        const val END_TIME = "end_time"
    }
}
