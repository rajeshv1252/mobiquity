package com.mobiquity.code.challenge.data.remote.response.fivedayweather

import com.google.gson.annotations.SerializedName


data class FiveDayResponse (
    @SerializedName("city")
    var city: City,

    @SerializedName("cnt")
    var cnt: Int ,

    @SerializedName("cod")
    var cod: String,

    @SerializedName("message")
    var message: Double,

    @SerializedName("list")
    var list: List<ItemHourly>
)