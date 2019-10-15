package com.github.harmittaa.koinexample.model

import com.github.harmittaa.koinexample.networking.Resource
import com.github.harmittaa.koinexample.networking.ResponseHandler
import com.github.harmittaa.koinexample.networking.WeatherApi
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.anyString
import retrofit2.HttpException

@RunWith(JUnit4::class)
class WeatherRepositoryTest {
    private val responseHandler = ResponseHandler()
    private lateinit var weatherApi: WeatherApi
    private lateinit var repository: WeatherRepository
    private val validLocation = "Helsinki"
    private val invalidLocation = "Somewhere else"
    private val weather = Weather(TempData(1.0, 1), "test")
    private val weatherResponse = Resource.success(weather)
    private val errorResponse = Resource.error("Unauthorised", null)


    @Before
    fun setUp() {
        weatherApi = mock()
        val mockException: HttpException = mock()
        whenever(mockException.code()).thenReturn(401)
        runBlocking {
            whenever(weatherApi.getForecast(eq(invalidLocation), anyString())).thenThrow(mockException)
            whenever(weatherApi.getForecast(eq(validLocation), anyString())).thenReturn(weather)
        }
        repository = WeatherRepository(weatherApi, responseHandler)
    }

    @Test
    fun `test getWeather when valid location is requested, then weather is returned`() =
        runBlocking {
            assertEquals(weatherResponse, repository.getWeather(validLocation))
        }

    @Test
    fun `test getWeather when invalid location is requested, then error is returned`() =
        runBlocking {
            assertEquals(errorResponse, repository.getWeather(invalidLocation))
        }
}