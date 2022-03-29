package com.lgtm.bubble_level.data


class BubbleLevelRepository(
    private val accelerometerDataSource: SensorDataSource
) : SensorDataSource by accelerometerDataSource
