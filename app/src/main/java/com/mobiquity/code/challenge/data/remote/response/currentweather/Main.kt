package com.mobiquity.code.challenge.data.remote.response.currentweather

import com.google.gson.annotations.SerializedName


data class Main(
    @SerializedName("temp")
    var temp: Double,

    @SerializedName("temp_min")
    var tempMin: Double,

    @SerializedName("grnd_level")
    var grndLevel: Double,

    @SerializedName("humidity")
    var humidity: Int,

    @SerializedName("pressure")
    var pressure: Double,

    @SerializedName("sea_level")
    var seaLevel: Double,

    @SerializedName("temp_max")
    var tempMax: Double

)