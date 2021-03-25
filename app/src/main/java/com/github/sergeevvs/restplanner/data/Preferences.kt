package com.github.sergeevvs.restplanner.data

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Preferences(context: Context) {

    private val preferences = context.getSharedPreferences(APP_SETTINGS, MODE_PRIVATE)

    var active: Boolean
        get() = preferences.getBoolean(ACTIVE, false)
        set(value) {
            preferences.edit().putBoolean(ACTIVE, value).apply()
        }

    var time: Int
        get() = preferences.getInt(TIME, 0)
        set(value) {
            preferences.edit().putInt(TIME, value).apply()
        }

    var days: Set<String>
        get() = preferences.getStringSet(DAYS, setOf()) as Set<String>
        set(value) {
            preferences.edit().putStringSet(DAYS, value).apply()
        }

    companion object {

        const val APP_SETTINGS = "app_settings"
        const val ACTIVE = "active"
        const val TIME = "time"
        const val DAYS = "days"
    }
}
