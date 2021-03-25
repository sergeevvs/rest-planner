package com.github.sergeevvs.restplanner

import android.app.Application
import com.github.sergeevvs.restplanner.data.Preferences
import com.github.sergeevvs.restplanner.presentation.viewmodels.ViewModelFactory

class App : Application() {

    private lateinit var preferences: Preferences
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()
        preferences = Preferences(this)
        viewModelFactory = ViewModelFactory(preferences)
    }
}