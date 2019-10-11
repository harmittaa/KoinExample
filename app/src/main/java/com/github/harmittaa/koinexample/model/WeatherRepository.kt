package com.github.harmittaa.koinexample.model

import com.github.harmittaa.koinexample.networking.Resource
import com.github.harmittaa.koinexample.networking.ResponseHandler
import com.github.harmittaa.koinexample.networking.WeatherApi
import org.koin.dsl.module
import retrofit2.HttpException

val forecastModule = module {
    factory { WeatherRepository(get(), get()) }
}

class WeatherRepository(private val weatherApi: WeatherApi, private val responseHandler: ResponseHandler) {
    suspend fun getWeather(): Resource<Weather> {
        return try {
            responseHandler.handleSuccess(weatherApi.getForecast())
        } catch (e: HttpException) {
            responseHandler.handleException(e.code())
        }
    }
}