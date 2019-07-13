package com.github.harmittaa.koinexample.networking

import com.github.harmittaa.koinexample.model.Weather
import retrofit2.http.GET

interface WeatherApi {
    @GET("weather?lat=60.72&lon=23.67&units=metric")
    suspend fun getForecast(): Weather
}