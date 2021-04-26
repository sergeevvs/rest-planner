package com.github.sergeevvs.restplanner.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.sergeevvs.restplanner.data.LOG_TAG
import com.github.sergeevvs.restplanner.databinding.FragmentTimeBinding
import com.github.sergeevvs.restplanner.presentation.viewmodels.TimeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TimeFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModel: TimeViewModel
    private val binding by lazy { FragmentTimeBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        binding.timePeriod.apply {
            minValue = 10
            maxValue = 90
            value = viewModel.notificationPeriod
            /*seton { view, scrollState ->
//                viewModel.notificationPeriod = newVal
                Log.d(LOG_TAG, "spinner listener ${view.value}")
            }*/
        }

        binding.startTimeHourPicker.apply {
            minValue = 0
            maxValue = 23
            value = viewModel.startHour
            setFormatter { String.format("%02d", it) }
            setOnValueChangedListener { _, _, newVal ->
                viewModel.startHour = newVal
            }
        }

        binding.startTimeMinutePicker.apply {
            minValue = 0
            maxValue = 59
            value = viewModel.startMinute
            setFormatter { String.format("%02d", it) }
            setOnValueChangedListener { _, _, newVal ->
                viewModel.startMinute = newVal
            }
        }

        binding.endTimeHourPicker.apply {
            minValue = 0
            maxValue = 23
            value = viewModel.endHour
            setFormatter { String.format("%02d", it) }
            setOnValueChangedListener { _, _, newVal ->
                viewModel.endHour = newVal
            }
        }

        binding.endTimeMinutePicker.apply {
            minValue = 0
            maxValue = 59
            value = viewModel.endMinute
            setFormatter { String.format("%02d", it) }
            setOnValueChangedListener { _, _, newVal ->
                viewModel.endMinute = newVal
            }
        }
    }
}
