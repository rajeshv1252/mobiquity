package com.mobiquity.code.challenge.data.remote.response.fivedayweather

import com.google.gson.annotations.SerializedName
import com.mobiquity.code.challenge.data.remote.response.common.Clouds
import com.mobiquity.code.challenge.data.remote.response.common.WeatherItem
import com.mobiquity.code.challenge.data.remote.response.common.Wind
import com.mobiquity.code.challenge.data.remote.response.currentweather.Sys


data class ItemHourly(
    @SerializedName("dt")
    var dt: Int,

    @SerializedName("dt_txt")
    var dtTxt: String,

    @SerializedName("weather")
    var weather: List<WeatherItem>,

    @SerializedName("main")
    var main: Main,

    @SerializedName("clouds")
    var clouds: Clouds,

    @SerializedName("sys")
    var sys: Sys,

    @SerializedName("wind")
    var wind: Wind,

    @SerializedName("rain")
    var rain: Rain

    )