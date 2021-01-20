package com.mobiquity.code.challenge.data.remote.response.common

import com.google.gson.annotations.SerializedName


data class Coord(
    @SerializedName("lon")
    var lon: Double,

    @SerializedName("lat")
    var lat: Double
)