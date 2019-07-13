package com.github.harmittaa.koinexample.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.harmittaa.koinexample.model.WeatherRepository
import com.github.harmittaa.koinexample.persistence.ExamplePreferences
import org.koin.dsl.module

val viewModelModule = module {
    factory { ExampleViewModel(get(), get()) }
}

class ExampleViewModel(
    preferences: ExamplePreferences,
    private val weatherRepo: WeatherRepository
) : ViewModel() {
    val fragmentContent = MutableLiveData<String>()
    val forecast: LiveData<String> = liveData {
        emit("Temperatur is: $weatherRepo.getWeather().main.temp")
    }

    init {
        fragmentContent.value = preferences.getFragmentContent()
    }
}
