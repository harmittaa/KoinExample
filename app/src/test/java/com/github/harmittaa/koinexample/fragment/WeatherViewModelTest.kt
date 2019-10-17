package com.github.harmittaa.koinexample.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.harmittaa.koinexample.model.TempData
import com.github.harmittaa.koinexample.model.Weather
import com.github.harmittaa.koinexample.model.WeatherRepository
import com.github.harmittaa.koinexample.networking.Resource
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class WeatherViewModelTest {
    private lateinit var viewModel: WeatherViewModel
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weatherObserver: Observer<Resource<Weather>>
    private val validLocation = "Helsinki"
    private val invalidLocation = "Helsinkii"
    private val successResource = Resource.success(Weather(TempData(1.0, 1), "test"))
    private val errorResource = Resource.error("Unauthorised", null)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        weatherRepository = mock()
        runBlocking {
            whenever(weatherRepository.getWeather(validLocation)).thenReturn(successResource)
            whenever(weatherRepository.getWeather(invalidLocation)).thenReturn(errorResource)
        }
        viewModel = WeatherViewModel(weatherRepository)
        weatherObserver = mock()
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `when getWeather is called with valid location, then observer is updated with success`() = runBlocking {
        viewModel.weather.observeForever(weatherObserver)
        viewModel.getWeather(validLocation)
        delay(10)
        verify(weatherRepository, times(1)).getWeather(validLocation)
        verify(weatherObserver, timeout(50).times(1)).onChanged(Resource.loading(null))
        verify(weatherObserver, timeout(50).times(1)).onChanged(successResource)
    }

    @Test
    fun `when getWeather is called with invalid location, then observer is updated with failure`() = runBlocking {
        viewModel.weather.observeForever(weatherObserver)
        viewModel.getWeather(invalidLocation)
        delay(10)
        verify(weatherRepository, times(1)).getWeather(invalidLocation)
        verify(weatherObserver, timeout(50).times(1)).onChanged(Resource.loading(null))
        verify(weatherObserver, timeout(50).times(1)).onChanged(errorResource)
    }
}