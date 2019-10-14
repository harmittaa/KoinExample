package com.github.harmittaa.koinexample.fragment

import androidx.lifecycle.*
import com.github.harmittaa.koinexample.model.WeatherRepository
import com.github.harmittaa.koinexample.networking.Resource
import org.koin.dsl.module

val viewModelModule = module {
    factory { ExampleViewModel(get()) }
}

class ExampleViewModel(
    private val weatherRepo: WeatherRepository
) : ViewModel() {

    private val location = MutableLiveData<String>()

    fun getWeather(input: String) {
        location.value = input
    }

    var weather = location.switchMap { location ->
        liveData() {
            emit(Resource.loading(null))
            emit(weatherRepo.getWeather(location))
        }
    }
}
