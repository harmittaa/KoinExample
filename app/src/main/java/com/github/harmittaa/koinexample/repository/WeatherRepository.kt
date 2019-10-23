package com.github.harmittaa.koinexample.repository

import com.github.harmittaa.koinexample.model.Weather
import com.github.harmittaa.koinexample.networking.Resource
import com.github.harmittaa.koinexample.networking.ResponseHandler
import com.github.harmittaa.koinexample.networking.WeatherApi
import org.koin.dsl.module

val forecastModule = module {
    factory { WeatherRepository(get(), get()) }
}

open class WeatherRepository(
    private val weatherApi: WeatherApi,
    private val responseHandler: ResponseHandler
) {

    suspend fun getWeather(location: String): Resource<Weather> {
        return try {
            val response = weatherApi.getForecast(location, "metric")
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}