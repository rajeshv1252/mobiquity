package com.mobiquity.code.challenge.data.remote.response.fivedayweather

import com.google.gson.annotations.SerializedName
import com.mobiquity.code.challenge.data.remote.response.common.Coord


class City(
    @SerializedName("country")
    var country: String,

    @SerializedName("coord")
    var coord: Coord,

    @SerializedName("name")
    var name: String,

    @SerializedName("id")
    var id: Int,

    @SerializedName("population")
    var population: Int
)