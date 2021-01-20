package com.mobiquity.code.challenge.data.model

import androidx.annotation.ColorInt


data class FiveDayWeather(
    var dt: Int,
    var temp: Double,
    var minTemp: Double,
    private var maxTemp: Double,
    var weatherId: Int,
    var timestampStart: Long,
    var timestampEnd: Long,
    @ColorInt
    var color: Int,
    @ColorInt
    var colorAlpha: Int
)