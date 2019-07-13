package com.github.harmittaa.koinexample.model

import com.github.harmittaa.koinexample.networking.WeatherApi
import org.koin.dsl.module

val forecastModule = module {
    factory { WeatherRepository(get()) }
}

class WeatherRepository(private val weatherApi: WeatherApi) {
    suspend fun getWeather() = weatherApi.getForecast()
}