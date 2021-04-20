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
    }

    private fun init() {
        binding.edTimePeriod.setText(viewModel.getTimePeriod())
    }

    override fun onStop() {
        super.onStop()
        viewModel.onSaveNotificationPeriod(binding.edTimePeriod.text.toString())
        viewModel.onFragmentStop()
    }
}
