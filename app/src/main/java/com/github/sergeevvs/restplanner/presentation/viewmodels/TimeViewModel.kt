package com.github.sergeevvs.restplanner.presentation.viewmodels

import javax.inject.Inject

class TimeViewModel @Inject constructor() : BaseViewModel() {

    fun getTimePeriod() = (prefRepository.notificationPeriod / 60 / 1000).toString()
    fun setNotificationPeriod(timePeriod: String) {
        prefRepository.notificationPeriod = (timePeriod.toLongOrNull() ?: 0) * 60 * 1000
    }

    fun getStartTime() = prefRepository.startTime.let {
        (it / 1000 / 60 / 60) to (it / 1000 / 60 % 60)
    }

    fun setStartTime(hour: Long, minute: Long) {
        prefRepository.startTime = ((hour * 60 * 60 * 1000) + (minute * 60 * 1000))
    }

    fun getEndTime() = prefRepository.endTime.let {
        (it / 1000 / 60 / 60) to (it / 1000 / 60 % 60)
    }

    fun setEndTime(hour: Long, minute: Long) {
        prefRepository.endTime = (hour * 60 * 60 * 1000) + (minute * 60 * 1000)
    }
}