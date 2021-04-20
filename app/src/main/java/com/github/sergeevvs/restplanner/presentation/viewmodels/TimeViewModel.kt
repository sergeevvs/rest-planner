package com.github.sergeevvs.restplanner.presentation.viewmodels

import javax.inject.Inject

class TimeViewModel @Inject constructor() : BaseViewModel() {

    fun onSaveNotificationPeriod(timePeriod: String) {
        prefRepository.notificationPeriod = (timePeriod.toLongOrNull() ?: 0) * 60 * 1000
    }

    fun getTimePeriod() = (prefRepository.notificationPeriod / 60 / 1000).toString()
}