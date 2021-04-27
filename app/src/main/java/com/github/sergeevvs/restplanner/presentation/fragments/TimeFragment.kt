package com.github.sergeevvs.restplanner.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import com.github.sergeevvs.restplanner.databinding.FragmentTimeBinding
import com.github.sergeevvs.restplanner.presentation.viewmodels.TimeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
            minValue = 1
            maxValue = 12
            displayedValues = (minValue..maxValue).map { (it * 10).toString() }.toTypedArray()
            value = viewModel.notificationPeriod
            setOnScrollListener { _, scrollState ->
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    GlobalScope.launch {
                        delay(1000L)
                        viewModel.notificationPeriod = value
                    }
                }
            }
        }

        binding.startTimeHourPicker.apply {
            minValue = 0
            maxValue = 23
            value = viewModel.startHour
            setFormatter { String.format("%02d", it) }
            setOnScrollListener { _, scrollState ->
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    GlobalScope.launch {
                        delay(1000L)
                        viewModel.startHour = value
                    }
                }
            }
        }

        binding.startTimeMinutePicker.apply {
            minValue = 0
            maxValue = 59
            value = viewModel.startMinute
            setFormatter { String.format("%02d", it) }
            setOnScrollListener { _, scrollState ->
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    GlobalScope.launch {
                        delay(1000L)
                        viewModel.startMinute = value
                    }
                }
            }
        }

        binding.endTimeHourPicker.apply {
            minValue = 0
            maxValue = 23
            value = viewModel.endHour
            setFormatter { String.format("%02d", it) }
            setOnScrollListener { _, scrollState ->
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    GlobalScope.launch {
                        delay(1000L)
                        viewModel.endHour = value
                    }
                }
            }
        }

        binding.endTimeMinutePicker.apply {
            minValue = 0
            maxValue = 59
            value = viewModel.endMinute
            setFormatter { String.format("%02d", it) }
            setOnScrollListener { _, scrollState ->
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    GlobalScope.launch {
                        delay(1000L)
                        viewModel.endMinute = value
                    }
                }
            }
        }
    }
}
