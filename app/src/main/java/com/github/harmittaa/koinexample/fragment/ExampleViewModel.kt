package com.github.harmittaa.koinexample.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.harmittaa.koinexample.persistence.ExamplePreferences
import org.koin.dsl.module

val viewModelModule = module {
    factory { ExampleViewModel(get()) }
}

class ExampleViewModel(preferences: ExamplePreferences) : ViewModel() {
    val fragmentContent = MutableLiveData<String>()

    init {
        fragmentContent.value = preferences.getFragmentContent()
    }
}
