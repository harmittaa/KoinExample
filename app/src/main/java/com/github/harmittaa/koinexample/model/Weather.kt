package com.github.harmittaa.koinexample.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("main") val temp: TempData,
    val name: String
)

data class TempData(
    val temp: Double,
    val humidity: Int
)
