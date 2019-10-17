package com.github.harmittaa.koinexample.networking

import com.github.harmittaa.koinexample.model.Weather
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getForecast(@Query("q")location: String,
                            @Query("units") unit: String): Weather
}