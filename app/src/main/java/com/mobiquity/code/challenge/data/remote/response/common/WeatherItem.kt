package com.mobiquity.code.challenge.data.remote.response.common

import com.google.gson.annotations.SerializedName


data class WeatherItem(
    @SerializedName("icon")
    var icon: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("main")
    var main: String,

    @SerializedName("id")
    var id: Int
)