package com.github.harmittaa.koinexample.model

data class Weather(
    val main: WeatherData
)

data class WeatherData(
    val temp: Double
)