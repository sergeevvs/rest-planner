package com.github.sergeevvs.restplanner.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.sergeevvs.restplanner.domain.interactors.PlannerInteractor

class ViewModelFactory(private val interactor: PlannerInteractor) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when (modelClass) {
            PlannerViewModel::class.java -> PlannerViewModel(interactor) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
}