package com.github.sergeevvs.restplanner.presentation.viewmodels

import com.github.sergeevvs.restplanner.data.Days
import javax.inject.Inject

class DaysViewModel @Inject constructor() : BaseViewModel() {

    fun isDayActive(day: Days) = prefRepository.isDayActive(day)

    fun onSaveDayActivating(day: Days, value: Boolean) {
        prefRepository.setDayActive(day, value)
    }
}