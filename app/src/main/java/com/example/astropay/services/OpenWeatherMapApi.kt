package com.example.astropay.services

import com.example.astropay.models.Weather
import com.example.astropay.network.ResultWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {
    @GET("weather")
    suspend fun getWeatherByCityName(@Query(CITY_PARAM) cityName: String): Weather

    @GET("weather")
    suspend fun getWeatherForCurrentLocation(
        @Query(LATITUDE_PARAM) latitude: Double,
        @Query(LONGITUDE_PARAM) longitude: Double
    ): Weather

}