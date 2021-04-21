package com.github.sergeevvs.restplanner.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.sergeevvs.restplanner.data.DAYS_FRAGMENT_TAG
import com.github.sergeevvs.restplanner.data.TIME_FRAGMENT_TAG
import com.github.sergeevvs.restplanner.databinding.FragmentMainBinding
import com.github.sergeevvs.restplanner.presentation.viewmodels.MainViewModel
import com.google.android.material.switchmaterial.SwitchMaterial
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel
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
        binding.switchPlannerState.isChecked = viewModel.plannerActive
    }

    private fun initListeners() {
        binding.switchPlannerState.setOnClickListener { onSwitchClicked(it) }
        binding.btnTime.setOnClickListener { onBtnTimeClicked() }
        binding.btnDays.setOnClickListener { onBtnDaysClicked() }
    }

    private fun onSwitchClicked(view: View) {
        viewModel.plannerActive = ((view as SwitchMaterial).isChecked)
    }

    private fun onBtnTimeClicked() {
        TimeFragment().show(parentFragmentManager, TIME_FRAGMENT_TAG)
    }

    private fun onBtnDaysClicked() {
        DaysFragment().show(parentFragmentManager, DAYS_FRAGMENT_TAG)
    }
}