package com.github.sergeevvs.restplanner.presentation.viewmodels

import javax.inject.Inject

class TimeViewModel @Inject constructor() : BaseViewModel() {

    var notificationPeriod: Int
        get() = prefRepository.notificationPeriod
        set(value) {
            prefRepository.notificationPeriod = value
        }

    var startHour: Int
        get() = prefRepository.startHour
        set(value) {
            prefRepository.startHour = value
        }

    var startMinute: Int
        get() = prefRepository.startMinute
        set(value) {
            prefRepository.startMinute = value
        }

    var endHour: Int
        get() = prefRepository.endHour
        set(value) {
            prefRepository.endHour = value
        }

    var endMinute: Int
        get() = prefRepository.endMinute
        set(value) {
            prefRepository.endMinute = value
        }
}