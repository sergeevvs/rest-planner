package com.github.sergeevvs.restplanner.data.repositories

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.github.sergeevvs.restplanner.data.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepository @Inject constructor(@ApplicationContext appContext: Context) {

    private val preferences = appContext.getSharedPreferences(APP_SETTINGS, MODE_PRIVATE)

    /*init {
        preferences.edit().clear().apply()
    }*/

    var plannerActive: Boolean
        get() = preferences.getBoolean(PLANNER_ACTIVE, false)
        set(value) {
            preferences.edit().putBoolean(PLANNER_ACTIVE, value).apply()
            Log.d(LOG_TAG, "Planner active has new value = $value")
        }

    var notificationPeriod: Int
        get() = preferences.getInt(NOTIFICATION_PERIOD, 10)
        set(value) {
            if (value >= 10) {
                preferences.edit().putInt(NOTIFICATION_PERIOD, value).apply()
                Log.d(LOG_TAG, "Notification period has new value = $value")
            }
        }

    var startHour: Int
        get() = preferences.getInt(START_HOUR, 0)
        set(value) {
            preferences.edit().putInt(START_HOUR, value).apply()
            Log.d(LOG_TAG, "Start hour has new value = $value")
        }

    var startMinute: Int
        get() = preferences.getInt(START_MINUTE, 0)
        set(value) {
            preferences.edit().putInt(START_MINUTE, value).apply()
            Log.d(LOG_TAG, "Start minute has new value = $value")
        }

    var endHour: Int
        get() = preferences.getInt(END_HOUR, 0)
        set(value) {
            preferences.edit().putInt(END_HOUR, value).apply()
            Log.d(LOG_TAG, "End hour has new value = $value")
        }

    var endMinute: Int
        get() = preferences.getInt(END_MINUTE, 0)
        set(value) {
            preferences.edit().putInt(END_MINUTE, value).apply()
            Log.d(LOG_TAG, "End minute has new value = $value")
        }

    fun isDayActive(day: Int) = preferences.getBoolean(day.toString(), false)

    fun setDayActive(day: Int, value: Boolean) {
        preferences.edit().putBoolean(day.toString(), value).apply()
        Log.d(LOG_TAG, "$day activating has new value = $value")
    }

    fun timeToMillis(hour: Int = 0, minute: Int = 0) =
        ((hour * 60 * 60 * 1000) + (minute * 60 * 1000)).toLong()

    fun millisToTime(millis: Long) =
        (millis / 1000 / 60 / 60).toInt() to (millis / 1000 / 60 % 60).toInt()
}
