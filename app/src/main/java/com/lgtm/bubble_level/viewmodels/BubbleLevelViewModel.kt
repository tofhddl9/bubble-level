package com.lgtm.bubble_level.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.lgtm.bubble_level.data.BubbleLevelRepository
import com.lgtm.bubble_level.mapper.BubbleLevelMapper.mapToBubbleLevelUiState
import com.lgtm.bubble_level.model.BubbleLevelUiState
import java.lang.IllegalArgumentException

class BubbleLevelViewModel(
    private val bubbleLevelRepository: BubbleLevelRepository
) : ViewModel() {

    fun getAccelerometerInfo(): LiveData<BubbleLevelUiState> =
        bubbleLevelRepository.getAccelerometer().asLiveData().map { it ->
            it.mapToBubbleLevelUiState()
        }

}

class ViewModelFactory(
    private val bubbleLevelRepository: BubbleLevelRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BubbleLevelViewModel::class.java)) {
            return BubbleLevelViewModel(bubbleLevelRepository) as T
        }
        throw IllegalArgumentException("cannot create BubbleLevelViewModel")
    }

}
