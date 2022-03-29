package com.lgtm.bubble_level.usecases

import com.lgtm.bubble_level.data.BubbleLevelRepository
import com.lgtm.bubble_level.model.AccelerometerVO

class GetAccelerometerUseCase(
    private val repository: BubbleLevelRepository
) {
    suspend fun invoke() {
        //return repository.getAccelerometer()
    }
}