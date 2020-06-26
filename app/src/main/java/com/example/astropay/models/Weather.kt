package com.example.astropay.models

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("main")
    val temp: TempData,

    @SerializedName("wind")
    val wind: Wind,

    @SerializedName("name")
    val cityName: String,

    @SerializedName("weather")
    val weatherStates: ArrayList<WeatherState>
)