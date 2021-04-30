package com.github.sergeevvs.restplanner.presentation.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.github.sergeevvs.restplanner.R
import com.github.sergeevvs.restplanner.data.DAYS_FRAGMENT_TAG
import com.github.sergeevvs.restplanner.databinding.FragmentMainBinding
import com.github.sergeevvs.restplanner.presentation.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val imm by lazy { requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListeners()
    }

    private fun init() {

        binding.switchPlannerState.isChecked = viewModel.plannerActive

        binding.edNotificationPeriod.setText(viewModel.notificationPeriod.toString())

        binding.startTimeHourPicker.apply {
            minValue = 0
            maxValue = 23
            value = viewModel.startHour
            setFormatter { String.format("%02d", it) }
        }

        binding.startTimeMinutePicker.apply {
            minValue = 0
            maxValue = 59
            value = viewModel.startMinute
            setFormatter { String.format("%02d", it) }
        }

        binding.endTimeHourPicker.apply {
            minValue = 0
            maxValue = 23
            value = viewModel.endHour
            setFormatter { String.format("%02d", it) }
        }

        binding.endTimeMinutePicker.apply {
            minValue = 0
            maxValue = 59
            value = viewModel.endMinute
            setFormatter { String.format("%02d", it) }
        }
    }

    private fun initListeners() {
        binding.btnDays.setOnClickListener { onBtnDaysClicked() }
        binding.btnSaveSettings.setOnClickListener { onBtnSaveClicked() }
    }

    private fun onBtnDaysClicked() {
        DaysFragment().show(parentFragmentManager, DAYS_FRAGMENT_TAG)
    }

    private fun onBtnSaveClicked() {

        imm.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)

        viewModel.notificationPeriod =
            with(binding.edNotificationPeriod.text.toString().toIntOrNull()) {
                if (this == null || this < 10) {
                    Snackbar.make(
                        requireView(),
                        R.string.notification_period_error,
                        Snackbar.LENGTH_LONG
                    ).setTextColor(Color.RED).show()
                    return
                } else this
            }
        viewModel.plannerActive = binding.switchPlannerState.isChecked
        viewModel.startHour = binding.startTimeHourPicker.value
        viewModel.startMinute = binding.startTimeMinutePicker.value
        viewModel.endHour = binding.endTimeHourPicker.value
        viewModel.endMinute = binding.endTimeMinutePicker.value

        Snackbar.make(requireView(), R.string.snackbar_save_settings, Snackbar.LENGTH_LONG)
            .setTextColor(Color.GREEN).show()

        viewModel.onSaveParams()
    }
}