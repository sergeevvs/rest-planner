package com.github.sergeevvs.restplanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.sergeevvs.restplanner.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater)

        binding.btnTime.setOnClickListener { onBtnTimeClicked() }

        return binding.root
    }

    private fun onBtnTimeClicked() {
        val bottomModal = TimeFragment().show(parentFragmentManager, "tag")
    }
}