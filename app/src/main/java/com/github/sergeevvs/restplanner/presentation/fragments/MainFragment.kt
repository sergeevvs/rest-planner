package com.github.sergeevvs.restplanner.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.sergeevvs.restplanner.App
import com.github.sergeevvs.restplanner.databinding.FragmentMainBinding
import com.github.sergeevvs.restplanner.presentation.viewmodels.PlannerViewModel
import com.google.android.material.switchmaterial.SwitchMaterial


class MainFragment : Fragment() {

    private val viewModel: PlannerViewModel by activityViewModels {
        (context?.applicationContext as App).viewModelFactory
    }
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }

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
        binding.switchPlannerEnabled.isChecked = viewModel.plannerActive
    }

    private fun initListeners() {
        binding.switchPlannerEnabled.setOnClickListener(this::onSwitchClicked)
        binding.btnTime.setOnClickListener(this::onBtnTimeClicked)
        binding.btnDays.setOnClickListener(this::onBtnDaysClicked)
    }

    private fun onSwitchClicked(view: View) {
        viewModel.plannerActive = (view as SwitchMaterial).isChecked
        viewModel.updateNotificationManager(requireContext().applicationContext)
    }

    private fun onBtnTimeClicked(view: View) {
        TimeFragment().show(parentFragmentManager, TIME_FRAGMENT_TAG)
    }

    private fun onBtnDaysClicked(view: View) {
        DaysFragment().show(parentFragmentManager, DAYS_FRAGMENT_TAG)
    }

    companion object {
        const val TIME_FRAGMENT_TAG = "Time fragment"
        const val DAYS_FRAGMENT_TAG = "Days fragment"
    }
}