package com.github.harmittaa.koinexample.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.harmittaa.koinexample.model.Weather
import com.github.harmittaa.koinexample.model.WeatherRepository
import com.github.harmittaa.koinexample.networking.Resource
import org.koin.dsl.module

val viewModelModule = module {
    factory { ExampleViewModel(get()) }
}

class ExampleViewModel(
    private val weatherRepo: WeatherRepository
) : ViewModel() {

    val weather: LiveData<Resource<Weather>> = liveData {
        emit(Resource.loading(null))
        emit(weatherRepo.getWeather())
    }
}
