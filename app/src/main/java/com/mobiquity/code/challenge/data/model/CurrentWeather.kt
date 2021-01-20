package com.mobiquity.code.challenge.data.model

data class CurrentWeather(
    var temp: Double,
    var humidity: Int,
    var description: String,
    var main: String,
    var weatherId: Int,
    var windDeg: Double,
    var windSpeed: Double,
    var storeTimestamp: Long
)