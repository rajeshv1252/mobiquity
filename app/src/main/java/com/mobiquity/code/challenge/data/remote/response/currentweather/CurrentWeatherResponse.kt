package com.mobiquity.code.challenge.data.remote.response.currentweather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mobiquity.code.challenge.data.remote.response.common.Clouds
import com.mobiquity.code.challenge.data.remote.response.common.Coord
import com.mobiquity.code.challenge.data.remote.response.common.WeatherItem
import com.mobiquity.code.challenge.data.remote.response.common.Wind


data class CurrentWeatherResponse(
    @Expose
    @SerializedName("dt")
    var dt: Int,
    @Expose
    @SerializedName("coord")
    var coord: Coord,
    @Expose
    @SerializedName("weather")
    var weather: List<WeatherItem>,
    @Expose
    @SerializedName("name")
    var name: String,
    @Expose
    @SerializedName("cod")
    var cod: Int,
    @Expose
    @SerializedName("main")
    var main: Main,
    @Expose
    @SerializedName("clouds")
    var clouds: Clouds,
    @Expose
    @SerializedName("id")
    var id: Int,
    @Expose
    @SerializedName("sys")
    var sys: Sys,
    @Expose
    @SerializedName("base")
    var base: String,
    @Expose
    @SerializedName("wind")
    var wind: Wind
)