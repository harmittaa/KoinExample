package com.github.harmittaa.koinexample.model

import com.github.harmittaa.koinexample.networking.WeatherApi
import org.koin.dsl.module
import java.lang.Exception

val forecastModule = module {
    factory { WeatherRepository(get()) }
}

class WeatherRepository(private val weatherApi: WeatherApi) {
    suspend fun getWeather(): Weather {
        return try {
            weatherApi.getForecast().body()!!
        } catch (e: Exception) {
            Weather(TempData(1.0, 2), "test")
        }
    }
}