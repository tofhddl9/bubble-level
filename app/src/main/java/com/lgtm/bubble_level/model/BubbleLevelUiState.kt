package com.lgtm.bubble_level.model

interface UiState

data class BubbleLevelUiState(
    val xTilt: Float,
    val yTilt: Float,
) : UiState {

    val isFlat: Boolean
        get() = (xTilt == 0.0f && yTilt == 0.0f)
}
