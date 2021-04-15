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

    var monday: Boolean
        get() = preferences.getBoolean(Days.MONDAY.toString(), false)
        set(value) {
            preferences.edit().putBoolean(Days.MONDAY.toString(), value).apply()
        }

    var tuesday: Boolean
        get() = preferences.getBoolean(Days.TUESDAY.toString(), false)
        set(value) {
            preferences.edit().putBoolean(Days.TUESDAY.toString(), value).apply()
        }

    var wednesday: Boolean
        get() = preferences.getBoolean(Days.WEDNESDAY.toString(), false)
        set(value) {
            preferences.edit().putBoolean(Days.WEDNESDAY.toString(), value).apply()
        }

    var thursday: Boolean
        get() = preferences.getBoolean(Days.THURSDAY.toString(), false)
        set(value) {
            preferences.edit().putBoolean(Days.THURSDAY.toString(), value).apply()
        }

    var friday: Boolean
        get() = preferences.getBoolean(Days.FRIDAY.toString(), false)
        set(value) {
            preferences.edit().putBoolean(Days.FRIDAY.toString(), value).apply()
        }

    var saturday: Boolean
        get() = preferences.getBoolean(Days.SATURDAY.toString(), false)
        set(value) {
            preferences.edit().putBoolean(Days.SATURDAY.toString(), value).apply()
        }

    var sunday: Boolean
        get() = preferences.getBoolean(Days.SUNDAY.toString(), false)
        set(value) {
            preferences.edit().putBoolean(Days.SUNDAY.toString(), value).apply()
        }

    companion object {
        const val APP_SETTINGS = "app_settings"
        const val ACTIVE = "active"
        const val TIME = "time"
    }
}
