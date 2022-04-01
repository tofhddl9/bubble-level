package com.lgtm.bubble_level.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.Dimension
import androidx.core.view.doOnLayout
import com.lgtm.bubble_level.databinding.ViewBubbleLevelCircleBinding
import com.lgtm.bubble_level.model.BubbleLevelUiState
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder

class BubbleLevelCircleView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr), BubbleLevelView {

    private var binding = ViewBubbleLevelCircleBinding.inflate(LayoutInflater.from(context), this, true)

    private var backgroundRadius: Int = 0
    private var waterDropRadius: Int = 0

    private var animator: ObjectAnimator? = null

    init {
        doOnLayout {
            backgroundRadius = binding.rootView.width / 2 - SAFE_AREA
            waterDropRadius = binding.waterDropView.width / 2
        }
    }

    override fun update(uiState: BubbleLevelUiState) {
        val tiltX = (backgroundRadius - waterDropRadius) / 9.8f * uiState.xTilt
        val tiltY = (backgroundRadius - waterDropRadius) / 9.8f * uiState.yTilt

        animateWaterDropView(tiltX, tiltY)
    }

    private fun animateWaterDropView(dx: Float, dy: Float) {
        animator?.cancel()

        val propertyTranslateX = PropertyValuesHolder.ofFloat(TRANSLATION_X, dx)
        val propertyTranslateY = PropertyValuesHolder.ofFloat(TRANSLATION_Y, dy)
        animator = ObjectAnimator.ofPropertyValuesHolder(binding.waterDropView, propertyTranslateX, propertyTranslateY)
        animator?.duration = ANIM_DURATION
        animator?.start()
    }

    companion object {
        @Dimension(unit = Dimension.DP)
        private const val SAFE_AREA = 30

        private const val ANIM_DURATION = 66L
    }
}