package com.lgtm.bubble_level.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class LifecycleAwareSensor(
    private val sensorManager: SensorManager,
    private val samplingPeriodUs: Int,
) : LifecycleEventObserver, SensorEventListener {

    abstract val sensor: Sensor

    private var lastSensorEvent : SensorEvent? = null

    val sensorEvents: Flow<SensorEvent> = flow {
        while(true) {
            lastSensorEvent?.let {
                emit(it)
            }
            delay(UI_UPDATE_TIME_IN_MILLIS)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        lastSensorEvent = event
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // do nothing
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_RESUME -> {
                sensorManager.registerListener(this, sensor, samplingPeriodUs)
            }
            Lifecycle.Event.ON_PAUSE -> {
                sensorManager.unregisterListener(this)
            }
            else -> {
                // do nothing
            }
        }
    }

    companion object {
        const val UI_UPDATE_TIME_IN_MILLIS = 16L
    }
}