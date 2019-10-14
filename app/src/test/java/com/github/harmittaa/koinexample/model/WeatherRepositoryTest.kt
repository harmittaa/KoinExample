package com.github.harmittaa.koinexample.model

import com.github.harmittaa.koinexample.networking.Resource
import com.github.harmittaa.koinexample.networking.ResponseHandler
import com.github.harmittaa.koinexample.networking.WeatherApi
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class WeatherRepositoryTest {
    private val responseHandler = ResponseHandler()
    private lateinit var weatherApi: WeatherApi
    lateinit var repository: WeatherRepository

    private val validLocation = "Helsinki"
    private val weather = Weather(TempData(1.0, 1), "test")
    private val weatherResponse = Resource.success(weather)


    @Before
    fun setUp() {
        weatherApi = mock()
        runBlocking {
            `when`(
                weatherApi.getForecast(
                    eq(validLocation),
                    ArgumentMatchers.anyString()
                )
            ).thenReturn(weather)
        }
        repository = WeatherRepository(weatherApi, responseHandler)
    }

    @Test
    fun `test getWeather when valid location is requested, then weather is returned`() =
        runBlocking {
            assertEquals(weatherResponse, repository.getWeather(validLocation))
        }
}