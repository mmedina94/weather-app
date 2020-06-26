package com.example.astropay.repositories

import com.example.astropay.models.Weather
import com.example.astropay.network.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface OpenWeatherRepository {
    suspend fun getWeatherByCityName(
        cityName: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): ResultWrapper<Weather>

    suspend fun getWeatherForCurrentLocation(
        latitude: Double,
        longitude: Double,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): ResultWrapper<Weather>
}