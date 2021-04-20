package com.github.sergeevvs.restplanner.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.view.children
import com.github.sergeevvs.restplanner.databinding.FragmentDaysBinding
import com.github.sergeevvs.restplanner.presentation.viewmodels.DaysViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
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
        binding.cbSunday.isChecked = viewModel.isDayActive(Calendar.SUNDAY)
        binding.cbMonday.isChecked = viewModel.isDayActive(Calendar.MONDAY)
        binding.cbTuesday.isChecked = viewModel.isDayActive(Calendar.TUESDAY)
        binding.cbWednesday.isChecked = viewModel.isDayActive(Calendar.WEDNESDAY)
        binding.cbThursday.isChecked = viewModel.isDayActive(Calendar.THURSDAY)
        binding.cbFriday.isChecked = viewModel.isDayActive(Calendar.FRIDAY)
        binding.cbSaturday.isChecked = viewModel.isDayActive(Calendar.SATURDAY)
    }

    override fun onStop() {
        super.onStop()
        for (cb in binding.cbGroup.children) if (cb is CheckBox) saveDayActivating(cb)
        viewModel.onFragmentStop()
    }

    private fun saveDayActivating(cb: CheckBox) {
        when (cb) {
            binding.cbSunday -> viewModel.onSaveDayActivating(Calendar.SUNDAY, cb.isChecked)
            binding.cbMonday -> viewModel.onSaveDayActivating(Calendar.MONDAY, cb.isChecked)
            binding.cbTuesday -> viewModel.onSaveDayActivating(Calendar.TUESDAY, cb.isChecked)
            binding.cbWednesday -> viewModel.onSaveDayActivating(Calendar.WEDNESDAY, cb.isChecked)
            binding.cbThursday -> viewModel.onSaveDayActivating(Calendar.THURSDAY, cb.isChecked)
            binding.cbFriday -> viewModel.onSaveDayActivating(Calendar.FRIDAY, cb.isChecked)
            binding.cbSaturday -> viewModel.onSaveDayActivating(Calendar.SATURDAY, cb.isChecked)
        }
    }
}