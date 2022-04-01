package com.lgtm.bubble_level.data

import com.lgtm.bubble_level.model.AccelerometerVO
import com.lgtm.bubble_level.sensor.AccelerometerSensor
import kotlin.math.roundToInt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccelerometerDataSource(
    private val sensor: AccelerometerSensor
) : SensorDataSource {

    override fun getAccelerometer(): Flow<AccelerometerVO> {
        return sensor.sensorEvents.map { event ->
            val accelerometer = AccelerometerVO(
                xAccel = (10f * event.values[0]).roundToInt() / 10f,
                yAccel = (10f * event.values[1]).roundToInt() / 10f,
                zAccel = (10f * event.values[2]).roundToInt() / 10f,
            )

            accelerometer
        }
    }
}
