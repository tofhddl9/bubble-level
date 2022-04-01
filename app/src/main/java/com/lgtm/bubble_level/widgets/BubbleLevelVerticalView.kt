package com.lgtm.bubble_level.widgets

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.Dimension
import androidx.core.view.doOnLayout
import com.lgtm.bubble_level.databinding.ViewBubbleLevelVerticalBinding
import com.lgtm.bubble_level.model.BubbleLevelUiState

class BubbleLevelVerticalView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr), BubbleLevelView {

    private var binding = ViewBubbleLevelVerticalBinding.inflate(LayoutInflater.from(context), this, true)

    private var backgroundRadius: Int = 0
    private var waterDropRadius: Int = 0

    private var animator: ObjectAnimator? = null

    init {
        doOnLayout {
            backgroundRadius = binding.rootView.height / 2 - SAFE_AREA
            waterDropRadius = binding.waterDropView.height / 2
        }
    }

    override fun update(uiState: BubbleLevelUiState) {
        val tiltY = (backgroundRadius - waterDropRadius) / 9.8f * uiState.yTilt

        animateWaterDropView(tiltY)
    }

    private fun animateWaterDropView(dy: Float) {
        animator?.cancel()

        animator = ObjectAnimator.ofFloat(binding.waterDropView, "translationY", dy)
        animator?.duration = ANIM_DURATION
        animator?.start()
    }

    companion object {
        @Dimension(unit = Dimension.DP)
        private const val SAFE_AREA = 45

        private const val ANIM_DURATION = 66L
    }
}
