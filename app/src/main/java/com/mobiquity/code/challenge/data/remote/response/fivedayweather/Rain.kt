package com.mobiquity.code.challenge.data.remote.response.fivedayweather

import com.google.gson.annotations.SerializedName


data class Rain(
    @SerializedName("3h")
    var jsonMember3h: Double
)