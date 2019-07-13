package com.github.harmittaa.koinexample.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("main") val temp: TempData
)

data class TempData(
    val temp: Double,
    val humidity: Int
)
