package com.github.harmittaa.koinexample.fragment

import androidx.lifecycle.*
import com.github.harmittaa.koinexample.repository.WeatherRepository
import com.github.harmittaa.koinexample.networking.Resource
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val viewModelModule = module {
    factory { WeatherViewModel(get()) }
}

class WeatherViewModel(
    private val weatherRepo: WeatherRepository
) : ViewModel() {

    private val location = MutableLiveData<String>()

    fun getWeather(input: String) {
        location.value = input
    }

    var weather = location.switchMap { location ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(weatherRepo.getWeather(location))
        }
    }
}
