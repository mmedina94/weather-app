package com.example.astropay.models

import com.example.astropay.utils.convertToCelsius
import com.google.gson.annotations.SerializedName

data class TempData(
    @SerializedName("temp")
    private val temp: Double,
    @SerializedName("temp_min")
    private val tempMin: Double,
    @SerializedName("temp_max")
    private val tempMax: Double,
    @SerializedName("humidity")
    val humidity: Int
) {
    fun getTemp(): Int = this.temp.convertToCelsius()

    fun getTempMin(): Int = this.tempMin.convertToCelsius()

    fun getTempMax(): Int = this.tempMax.convertToCelsius()

}