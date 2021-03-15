package com.github.sergeevvs.restplanner

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.github.sergeevvs.restplanner.databinding.FragmentTimeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TimeFragment : BottomSheetDialogFragment() {

    private val sharedPref by lazy { activity?.getPreferences(Context.MODE_PRIVATE) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentTimeBinding.inflate(layoutInflater)

        /* init block */
        binding.rgTime.check(sharedPref?.getInt(getString(R.string.notification_period_id), 0) ?: 0)

        /* listeners */
        binding.rgTime.setOnCheckedChangeListener(this::saveNotificationPeriodId)

        return binding.root
    }

    private fun saveNotificationPeriodId(radioGroup: RadioGroup, i: Int) {
        sharedPref?.edit()?.putInt(getString(R.string.notification_period_id), i)?.apply()
    }
}
