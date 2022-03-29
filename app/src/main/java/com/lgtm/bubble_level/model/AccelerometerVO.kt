package com.lgtm.bubble_level.model

interface VO

data class AccelerometerVO(
    val xTilt: Float,
    val yTilt: Float,
) : VO



interface UiState

data class BubbleLevelUiState(
    val xTilt: Float,
    val yTilt: Float,
) : UiState