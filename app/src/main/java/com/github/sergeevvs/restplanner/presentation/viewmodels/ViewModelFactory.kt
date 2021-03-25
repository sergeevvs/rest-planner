package com.github.sergeevvs.restplanner.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.sergeevvs.restplanner.data.Preferences

class ViewModelFactory(
    private val preferences: Preferences
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when (modelClass) {
            PlannerViewModel::class.java -> PlannerViewModel(preferences) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
}