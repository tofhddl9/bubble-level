package com.lgtm.bubble_level.widgets

import com.lgtm.bubble_level.model.BubbleLevelUiState

interface BubbleLevelView {
    fun update(uiState: BubbleLevelUiState)
}
