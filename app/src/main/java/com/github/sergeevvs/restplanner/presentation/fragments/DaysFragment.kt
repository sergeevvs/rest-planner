package com.github.sergeevvs.restplanner.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.github.sergeevvs.restplanner.App
import com.github.sergeevvs.restplanner.databinding.FragmentDaysBinding
import com.github.sergeevvs.restplanner.presentation.viewmodels.PlannerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.switchmaterial.SwitchMaterial

class DaysFragment : BottomSheetDialogFragment() {

    private val viewModel: PlannerViewModel by activityViewModels {
        (context?.applicationContext as App).viewModelFactory
    }

    private val binding by lazy { FragmentDaysBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListeners()
    }

    private fun init() {
        binding.switchMonday.isChecked = viewModel.monday
        binding.switchTuesday.isChecked = viewModel.tuesday
        binding.switchWednesday.isChecked = viewModel.wednesday
        binding.switchThursday.isChecked = viewModel.thursday
        binding.switchFriday.isChecked = viewModel.friday
        binding.switchSaturday.isChecked = viewModel.saturday
        binding.switchSunday.isChecked = viewModel.sunday
    }

    private fun initListeners() {
        binding.switchMonday.setOnClickListener(this::saveDayActivating)
        binding.switchTuesday.setOnClickListener(this::saveDayActivating)
        binding.switchWednesday.setOnClickListener(this::saveDayActivating)
        binding.switchThursday.setOnClickListener(this::saveDayActivating)
        binding.switchFriday.setOnClickListener(this::saveDayActivating)
        binding.switchSaturday.setOnClickListener(this::saveDayActivating)
        binding.switchSunday.setOnClickListener(this::saveDayActivating)
    }

    private fun saveDayActivating(view: View) {
        when (view as SwitchMaterial) {
            binding.switchMonday -> viewModel.monday = view.isChecked
            binding.switchTuesday -> viewModel.tuesday = view.isChecked
            binding.switchWednesday -> viewModel.wednesday = view.isChecked
            binding.switchThursday -> viewModel.thursday = view.isChecked
            binding.switchFriday -> viewModel.friday = view.isChecked
            binding.switchSaturday -> viewModel.saturday = view.isChecked
            binding.switchSunday -> viewModel.sunday = view.isChecked
        }
    }
}