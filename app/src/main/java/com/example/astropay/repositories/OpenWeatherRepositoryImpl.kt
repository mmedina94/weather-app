package com.example.astropay.repositories

import com.example.astropay.models.Weather
import com.example.astropay.services.OpenWeatherMapApi
import com.example.astropay.network.ResultWrapper
import com.example.astropay.network.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class OpenWeatherRepositoryImpl constructor(private val api: OpenWeatherMapApi) :
    OpenWeatherRepository {

    override suspend fun getWeatherByCityName(
        cityName: String,
        dispatcher: CoroutineDispatcher
    ): ResultWrapper<Weather> {
        return safeApiCall(
            dispatcher = dispatcher,
            apiCall = { api.getWeatherByCityName(cityName) }
        )
    }

    override suspend fun getWeatherForCurrentLocation(
        latitude: Double,
        longitude: Double,
        dispatcher: CoroutineDispatcher
    ): ResultWrapper<Weather> {
        return safeApiCall(
            dispatcher = dispatcher,
            apiCall = {
                api.getWeatherForCurrentLocation(
                    latitude,
                    longitude
                )
            }
        )
    }
}