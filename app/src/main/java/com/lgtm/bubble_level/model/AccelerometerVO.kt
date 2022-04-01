package com.lgtm.bubble_level.model

interface VO

data class AccelerometerVO(
    val xAccel: Float,
    val yAccel: Float,
    val zAccel: Float,
) : VO
