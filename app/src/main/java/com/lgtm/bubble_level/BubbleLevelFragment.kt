package com.lgtm.bubble_level

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lgtm.bubble_level.data.AccelerometerDataSource
import com.lgtm.bubble_level.data.BubbleLevelRepository
import com.lgtm.bubble_level.databinding.FragmentBubbleLevelBinding
import com.lgtm.bubble_level.sensor.AccelerometerSensor
import com.lgtm.bubble_level.viewmodels.BubbleLevelViewModel
import com.lgtm.bubble_level.viewmodels.ViewModelFactory

class BubbleLevelFragment: Fragment() {

    private lateinit var binding: FragmentBubbleLevelBinding

    private lateinit var viewModel: BubbleLevelViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBubbleLevelBinding.inflate(inflater, container, false)

        initViewModel()
        observeViewModel()

        return binding.root
    }

    private fun initViewModel() {
        val accelerometerSensor = AccelerometerSensor(
            requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        ).also {
            lifecycle.addObserver(it)
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(BubbleLevelRepository(AccelerometerDataSource(accelerometerSensor)))
        ).get(BubbleLevelViewModel::class.java)
    }

    private fun observeViewModel() {
        viewModel.getAccelerometerInfo().observe(this) { bubbleLevelUiState ->
            binding.tempView.text = "X : ${bubbleLevelUiState.xTilt} Y : ${bubbleLevelUiState.yTilt}"
        }
    }

    private fun updateCompassArrow(view: View, nextAzimuth: Float, currAzimuth: Float) {
        val anim = RotateAnimation(currAzimuth, nextAzimuth,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f).apply {
            duration = 300
            repeatCount = 0
            fillAfter = true
        }

        view.startAnimation(anim)
    }

    private fun updateCompassDirectionInfo(view: TextView, nextAzimuth: Float, point: String) {
        view.text = "${nextAzimuth.toInt()}${"\u00B0"} $point"
    }

}
