package com.github.sergeevvs.restplanner.presentation.viewmodels

import androidx.lifecycle.ViewModel
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

    var days: Set<String>
        get() = preferences.days
        set(value) {
            preferences.days = value
        }
}