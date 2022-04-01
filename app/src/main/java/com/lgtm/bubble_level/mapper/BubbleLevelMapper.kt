package com.lgtm.bubble_level.mapper

import com.lgtm.bubble_level.model.AccelerometerVO
import com.lgtm.bubble_level.model.BubbleLevelUiState

object BubbleLevelMapper {

    fun AccelerometerVO.mapToBubbleLevelUiState(): BubbleLevelUiState {
        return BubbleLevelUiState(
            xTilt = this.xAccel,
            yTilt = this.yAccel,
        )
    }

}
