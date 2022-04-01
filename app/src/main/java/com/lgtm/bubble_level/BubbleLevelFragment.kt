package com.lgtm.bubble_level

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lgtm.bubble_level.data.AccelerometerDataSource
import com.lgtm.bubble_level.data.BubbleLevelRepository
import com.lgtm.bubble_level.databinding.FragmentBubbleLevelBinding
import com.lgtm.bubble_level.model.BubbleLevelUiState
import com.lgtm.bubble_level.sensor.AccelerometerSensor
import com.lgtm.bubble_level.viewmodels.BubbleLevelViewModel
import com.lgtm.bubble_level.viewmodels.ViewModelFactory

private const val UNICODE_ANGLE = "\u00B0"

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
        viewModel.bubbleLevelViewInfo.observe(viewLifecycleOwner) { bubbleLevelUiState ->
            updateBubbleLevelViews(bubbleLevelUiState)
        }

        viewModel.bubbleLevelBillBoardInfo.observe(viewLifecycleOwner) { bubbleLevelUiState ->
            updateBubbleLevelBillBoardText(bubbleLevelUiState)
        }

        viewModel.isBubbleLevelFlat.observe(viewLifecycleOwner) { isFlat ->
            updateBubbleLevelBillBoardColor(isFlat)
        }

    }

    private fun updateBubbleLevelViews(bubbleLevelUiState: BubbleLevelUiState) {
        binding.bubbleLevelCircleView.update(bubbleLevelUiState)
        binding.bubbleLevelHorizontalView.update(bubbleLevelUiState)
        binding.bubbleLevelVerticalView.update(bubbleLevelUiState)
    }

    private fun updateBubbleLevelBillBoardText(bubbleLevelUiState: BubbleLevelUiState) {
        binding.bubbleLevelBillBoard.text =
            "X = ${bubbleLevelUiState.xTilt}${UNICODE_ANGLE}   Y = ${bubbleLevelUiState.yTilt}${UNICODE_ANGLE}"
    }

    private fun updateBubbleLevelBillBoardColor(isFlat: Boolean) {
        val backgroundColor = if (isFlat) {
            R.color.flat_bubble_level_bill_board_background_color
        } else {
            R.color.inclined_bubble_level_bill_board_background_color
        }
        (binding.bubbleLevelBillBoard.background as? GradientDrawable)?.setColor(ContextCompat.getColor(requireContext(), backgroundColor))
    }

}
