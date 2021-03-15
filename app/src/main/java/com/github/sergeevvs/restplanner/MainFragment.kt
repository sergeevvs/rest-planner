package com.github.sergeevvs.restplanner

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.github.sergeevvs.restplanner.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial


class MainFragment : Fragment() {

    private val sharedPref by lazy { activity?.getPreferences(Context.MODE_PRIVATE) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater)

        createNotificationChannel()

        /* init block */
        binding.switchRestPlannerEnabled.isChecked =
            sharedPref?.getBoolean(getString(R.string.is_rest_planner_checked), false) ?: false

        /* listeners */
        binding.switchRestPlannerEnabled.setOnClickListener(this::onSwitchClicked)
        binding.btnTime.setOnClickListener(this::onBtnTimeClicked)
        binding.btnDays.setOnClickListener(this::onBtnDaysClicked)

        return binding.root
    }

    private fun onBtnTimeClicked(view: View) {
        TimeFragment().show(parentFragmentManager, TIME_FRAGMENT_TAG)
    }

    private fun onBtnDaysClicked(view: View) {
        Snackbar.make(view, "Days will be enabled later.", Snackbar.LENGTH_LONG).show()

        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Rest planner")
            .setContentText("Вам пора отдохнуть.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext())) {
            notify(0, builder.build())
        }
    }

    private fun onSwitchClicked(view: View) {
        sharedPref?.edit()?.putBoolean(
            getString(R.string.is_rest_planner_checked),
            (view as SwitchMaterial).isChecked
        )?.apply()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {

        const val TIME_FRAGMENT_TAG = "Time fragment"
        const val CHANNEL_ID = "rest_planner_channel_id"
    }
}