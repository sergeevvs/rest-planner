package com.github.sergeevvs.restplanner.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.view.children
import com.github.sergeevvs.restplanner.data.Days
import com.github.sergeevvs.restplanner.databinding.FragmentDaysBinding
import com.github.sergeevvs.restplanner.presentation.viewmodels.DaysViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DaysFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModel: DaysViewModel
    private val binding by lazy { FragmentDaysBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.cbMonday.isChecked = viewModel.isDayActive(Days.MONDAY)
        binding.cbTuesday.isChecked = viewModel.isDayActive(Days.TUESDAY)
        binding.cbWednesday.isChecked = viewModel.isDayActive(Days.WEDNESDAY)
        binding.cbThursday.isChecked = viewModel.isDayActive(Days.THURSDAY)
        binding.cbFriday.isChecked = viewModel.isDayActive(Days.FRIDAY)
        binding.cbSaturday.isChecked = viewModel.isDayActive(Days.SATURDAY)
        binding.cbSunday.isChecked = viewModel.isDayActive(Days.SUNDAY)
    }

    override fun onStop() {
        super.onStop()
        for (cb in binding.cbGroup.children) if (cb is CheckBox) saveDayActivating(cb)
        viewModel.onFragmentStop()
    }

    private fun saveDayActivating(cb: CheckBox) {
        when (cb) {
            binding.cbMonday -> viewModel.onSaveDayActivating(Days.MONDAY, cb.isChecked)
            binding.cbTuesday -> viewModel.onSaveDayActivating(Days.TUESDAY, cb.isChecked)
            binding.cbWednesday -> viewModel.onSaveDayActivating(Days.WEDNESDAY, cb.isChecked)
            binding.cbThursday -> viewModel.onSaveDayActivating(Days.THURSDAY, cb.isChecked)
            binding.cbFriday -> viewModel.onSaveDayActivating(Days.FRIDAY, cb.isChecked)
            binding.cbSaturday -> viewModel.onSaveDayActivating(Days.SATURDAY, cb.isChecked)
            binding.cbSunday -> viewModel.onSaveDayActivating(Days.SUNDAY, cb.isChecked)
        }
    }
}