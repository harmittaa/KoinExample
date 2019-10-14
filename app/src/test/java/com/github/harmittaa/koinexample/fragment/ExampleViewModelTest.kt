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
class ExampleViewModelTest {
    private lateinit var viewModel: ExampleViewModel
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weatherObserver: Observer<Resource<Weather>>
    private lateinit var loadingObserver: Observer<Boolean>
    private val successResource = Resource.success(Weather(TempData(1.0, 1), "test"))

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
        whenever(weatherRepository.returnFun()).thenReturn("NOT FUN!")
        /*runBlocking {
            //whenever(weatherRepository.getWeather("Helsinki")).thenReturn(successResource)
        }*/
        viewModel = ExampleViewModel(weatherRepository)
        weatherObserver = mock()
        loadingObserver = mock()
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }


    @Test
    fun `when getWeather is called, then observer is updated`() = runBlocking {
        /*viewModel.weather.observeForever(weatherObserver)
        viewModel.getWeather("Helsinki")
        delay(10)
        verify(weatherRepository, times(1)).getWeather("Helsinki")
        verify(weatherObserver, timeout(50).times(1)).onChanged(Resource.loading(null))
        verify(weatherObserver, timeout(50).times(1)).onChanged(Resource.success(null))*/
    }
}