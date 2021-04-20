package com.github.sergeevvs.restplanner.presentation.viewmodels

import javax.inject.Inject

class DaysViewModel @Inject constructor() : BaseViewModel() {

    fun isDayActive(day: Int) = prefRepository.isDayActive(day)

    fun onSaveDayActivating(day: Int, value: Boolean) {
        prefRepository.setDayActive(day, value)
    }
}