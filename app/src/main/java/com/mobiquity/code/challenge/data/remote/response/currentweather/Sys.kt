package com.mobiquity.code.challenge.data.remote.response.currentweather

import com.google.gson.annotations.SerializedName


data class Sys(
    @SerializedName("country")
    var country: String,

    @SerializedName("sunrise")
    var sunrise: Int,

    @SerializedName("sunset")
    var sunset: Int,

    @SerializedName("message")
    var message: Double
)