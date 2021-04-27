package com.github.sergeevvs.restplanner.presentation.viewmodels

import javax.inject.Inject

class TimeViewModel @Inject constructor() : BaseViewModel() {

    var notificationPeriod: Int
        get() = prefRepository.notificationPeriod / 10
        set(value) {
            prefRepository.notificationPeriod = value * 10
            updateNotificationManager()
        }

    var startHour: Int
        get() = prefRepository.startHour
        set(value) {
            prefRepository.startHour = value
            updateNotificationManager()
        }

    var startMinute: Int
        get() = prefRepository.startMinute
        set(value) {
            prefRepository.startMinute = value
            updateNotificationManager()
        }

    var endHour: Int
        get() = prefRepository.endHour
        set(value) {
            prefRepository.endHour = value
            updateNotificationManager()
        }

    var endMinute: Int
        get() = prefRepository.endMinute
        set(value) {
            prefRepository.endMinute = value
            updateNotificationManager()
        }
}