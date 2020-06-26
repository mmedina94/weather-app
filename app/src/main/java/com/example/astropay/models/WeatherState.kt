package com.example.astropay.models

import com.google.gson.annotations.SerializedName

data class WeatherState(
    @SerializedName("icon")
    val icon: String
)