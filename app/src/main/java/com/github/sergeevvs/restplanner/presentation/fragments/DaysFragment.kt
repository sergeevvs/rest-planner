package com.github.sergeevvs.restplanner.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.view.children
import androidx.fragment.app.activityViewModels
import com.github.sergeevvs.restplanner.App
import com.github.sergeevvs.restplanner.databinding.FragmentDaysBinding
import com.github.sergeevvs.restplanner.presentation.viewmodels.PlannerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

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
        binding.cbMonday.isChecked = viewModel.monday
        binding.cbTuesday.isChecked = viewModel.tuesday
        binding.cbWednesday.isChecked = viewModel.wednesday
        binding.cbThursday.isChecked = viewModel.thursday
        binding.cbFriday.isChecked = viewModel.friday
        binding.cbSaturday.isChecked = viewModel.saturday
        binding.cbSunday.isChecked = viewModel.sunday
    }

    private fun initListeners() {
        for (cb in binding.cbGroup.children)
            if (cb is CheckBox) cb.setOnClickListener(this::saveDayActivating)
    }

    private fun saveDayActivating(view: View) {
        when (view as CheckBox) {
            binding.cbMonday -> viewModel.monday = view.isChecked
            binding.cbTuesday -> viewModel.tuesday = view.isChecked
            binding.cbWednesday -> viewModel.wednesday = view.isChecked
            binding.cbThursday -> viewModel.thursday = view.isChecked
            binding.cbFriday -> viewModel.friday = view.isChecked
            binding.cbSaturday -> viewModel.saturday = view.isChecked
            binding.cbSunday -> viewModel.sunday = view.isChecked
        }
        Log.d(App.LOG_TAG, "Set ${view.text} activating in ${view.isChecked}")
    }
}