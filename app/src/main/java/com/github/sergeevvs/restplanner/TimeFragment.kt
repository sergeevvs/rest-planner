package com.github.sergeevvs.restplanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.sergeevvs.restplanner.databinding.FragmentTimeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TimeFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentTimeBinding.inflate(layoutInflater)

        return binding.root
    }
}