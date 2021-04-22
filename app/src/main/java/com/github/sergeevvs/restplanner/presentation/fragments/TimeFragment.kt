package com.github.sergeevvs.restplanner.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.sergeevvs.restplanner.R
import com.github.sergeevvs.restplanner.data.END_TIME_PICKER_TAG
import com.github.sergeevvs.restplanner.data.START_TIME_PICKER_TAG
import com.github.sergeevvs.restplanner.databinding.FragmentTimeBinding
import com.github.sergeevvs.restplanner.presentation.viewmodels.TimeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat
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
        binding.tvStartTime.text =
            viewModel.getStartTime().run { getString(R.string.time_template, first, second) }
        binding.tvEndTime.text =
            viewModel.getEndTime().run { getString(R.string.time_template, first, second) }
    }

    private fun initListeners() {
        binding.tvStartTime.setOnClickListener { onStartTimeClicked() }
        binding.tvEndTime.setOnClickListener { onEndTimeClicked() }
    }

    private fun onStartTimeClicked() {
        MaterialTimePicker.Builder()
            .setInputMode(INPUT_MODE_KEYBOARD)
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .apply {
                viewModel.getStartTime().let {
                    setHour(it.first.toInt())
                    setMinute(it.second.toInt())
                }
            }
            .build()
            .apply {
                addOnPositiveButtonClickListener {
                    viewModel.setStartTime(hour.toLong(), minute.toLong())
                    binding.tvStartTime.text = getString(R.string.time_template, hour, minute)
                }
            }
            .show(parentFragmentManager, START_TIME_PICKER_TAG)
    }

    private fun onEndTimeClicked() {
        MaterialTimePicker.Builder()
            .setInputMode(INPUT_MODE_KEYBOARD)
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .apply {
                viewModel.getEndTime().let {
                    setHour(it.first.toInt())
                    setMinute(it.second.toInt())
                }
            }
            .build()
            .apply {
                addOnPositiveButtonClickListener {
                    viewModel.setEndTime(hour.toLong(), minute.toLong())
                    binding.tvEndTime.text = getString(R.string.time_template, hour, minute)
                }
            }
            .show(parentFragmentManager, END_TIME_PICKER_TAG)
    }

    override fun onStop() {
        super.onStop()
        viewModel.setNotificationPeriod(binding.edTimePeriod.text.toString())
        viewModel.onFragmentStop()
    }
}
