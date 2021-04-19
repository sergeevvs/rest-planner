package com.github.sergeevvs.restplanner

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        const val LOG_TAG = "rest_planner_log"
        const val CHANNEL_ID = "rest_planner_channel_id"
    }
}