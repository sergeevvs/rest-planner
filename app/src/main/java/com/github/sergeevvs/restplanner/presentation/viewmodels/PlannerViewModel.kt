package com.github.sergeevvs.restplanner.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.github.sergeevvs.restplanner.data.Days
import com.github.sergeevvs.restplanner.data.Preferences

class PlannerViewModel(
    private val preferences: Preferences
) : ViewModel() {

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
}