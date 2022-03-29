package com.lgtm.bubble_level.sensor

import android.hardware.Sensor
import android.hardware.SensorManager

class AccelerometerSensor(
    private val sensorManager: SensorManager,
    samplingPeriodUs: Int = SensorManager.SENSOR_DELAY_UI
) : LifecycleAwareSensor(sensorManager, samplingPeriodUs) {

    override val sensor: Sensor by lazy {
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

}
