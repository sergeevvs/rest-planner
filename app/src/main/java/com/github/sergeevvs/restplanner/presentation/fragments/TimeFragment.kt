package com.github.sergeevvs.restplanner.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        initListeners()
    }

    private fun init() {
        binding.edTimePeriod.setText(viewModel.getTimePeriod())

        // Time picker testing
        binding.startTimeHourPicker.apply {
            minValue = 0
            maxValue = 23
            setFormatter { String.format("%02d", it) }
            setOnValueChangedListener { picker, oldVal, newVal ->

            }
        }
        binding.startTimeMinutePicker.apply {
            minValue = 0
            maxValue = 59
            setFormatter { String.format("%02d", it) }
            isSoundEffectsEnabled = true
        }
        binding.endTimeHourPicker.apply {
            minValue = 0
            maxValue = 23
            setFormatter { String.format("%02d", it) }
        }
        binding.endTimeMinutePicker.apply {
            minValue = 0
            maxValue = 59
            setFormatter { String.format("%02d", it) }
        }

        viewModel.getStartTime().apply {
            binding.startTimeHourPicker.value = first.toInt()
            binding.startTimeMinutePicker.value = second.toInt()
        }
        viewModel.getEndTime().apply {
            binding.endTimeHourPicker.value = first.toInt()
            binding.endTimeMinutePicker.value = second.toInt()
        }
    }

    private fun initListeners() {
    }

    override fun onStop() {
        super.onStop()
        viewModel.setNotificationPeriod(binding.edTimePeriod.text.toString())
        viewModel.setStartTime(
            binding.startTimeHourPicker.value.toLong(),
            binding.startTimeMinutePicker.value.toLong()
        )
        viewModel.setEndTime(
            binding.endTimeHourPicker.value.toLong(),
            binding.endTimeMinutePicker.value.toLong()
        )
        viewModel.onFragmentStop()
    }
}
