package com.github.sergeevvs.restplanner.presentation.viewmodels

import android.content.res.Resources
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.children
import com.github.sergeevvs.restplanner.R
import javax.inject.Inject

class TimeViewModel @Inject constructor() : BaseViewModel() {

    fun onSaveNotificationPeriod(resources: Resources, radioGroup: RadioGroup) {
        for (rb in radioGroup.children)
            if ((rb as RadioButton).isChecked) {
                prefRepository.notificationPeriod = getTimeByRadioButton(resources, rb)
                break
            }
    }

    private fun getTimeByRadioButton(resources: Resources, rb: RadioButton) = when (rb.text) {
        resources.getString(R.string.every_15_minutes) -> 15
        resources.getString(R.string.every_30_minutes) -> 30
        resources.getString(R.string.every_45_minutes) -> 45
        resources.getString(R.string.every_60_minutes) -> 60
        resources.getString(R.string.every_90_minutes) -> 90
        else -> 0
    }

    fun getRadioButtonByTime() = when (prefRepository.notificationPeriod) {
        15 -> R.id.every_15_minutes
        30 -> R.id.every_30_minutes
        45 -> R.id.every_45_minutes
        60 -> R.id.every_60_minutes
        90 -> R.id.every_90_minutes
        else -> 0
    }
}