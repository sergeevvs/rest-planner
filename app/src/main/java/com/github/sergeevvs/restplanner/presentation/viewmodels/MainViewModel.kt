package com.github.sergeevvs.restplanner.presentation.viewmodels

import android.util.Log
import com.github.sergeevvs.restplanner.data.LOG_TAG
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    var plannerActive
        get() = prefRepository.plannerActive
        set(value) {
            prefRepository.plannerActive = value
            updateNotificationManager()
            Log.d(LOG_TAG, "Change planner active on $value")
        }
}