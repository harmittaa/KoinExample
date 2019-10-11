package com.github.harmittaa.koinexample.networking

import com.github.harmittaa.koinexample.model.Weather
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApi {
    @GET("weather?q=Helsinki&units=metric")
    suspend fun getForecast(): Weather
}