package com.github.sergeevvs.restplanner.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import com.github.sergeevvs.restplanner.App
import com.github.sergeevvs.restplanner.databinding.FragmentTimeBinding
import com.github.sergeevvs.restplanner.presentation.viewmodels.PlannerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TimeFragment : BottomSheetDialogFragment() {

    private val viewModel: PlannerViewModel by activityViewModels {
        (context?.applicationContext as App).viewModelFactory
    }
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
        binding.rgTime.check(viewModel.plannerTime)
    }

    private fun initListeners() {
        binding.rgTime.setOnCheckedChangeListener(this::saveNotificationPeriodId)
    }

    private fun saveNotificationPeriodId(radioGroup: RadioGroup, i: Int) {
        viewModel.plannerTime = i
    }
}
