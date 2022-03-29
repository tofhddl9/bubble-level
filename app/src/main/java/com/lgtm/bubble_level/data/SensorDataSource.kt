package com.lgtm.bubble_level.data

import com.lgtm.bubble_level.model.AccelerometerVO
import kotlinx.coroutines.flow.Flow

interface SensorDataSource {
    fun getAccelerometer(): Flow<AccelerometerVO>
}
