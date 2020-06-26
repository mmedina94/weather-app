package com.example.astropay.ui.customview

import com.example.astropay.BuildConfig
import com.example.astropay.models.Weather
import com.example.astropay.utils.ZERO_VALUE
import com.example.astropay.utils.getDoubleWithTwoDecimalsString

class WeatherViewHandler {

    fun init(weather: Weather, weatherCallback: WeatherCallback) {
        weatherCallback.loadCityName(cityName = weather.cityName)
        weatherCallback.loadImage(imageUrl = getImageUrl(weather.weatherStates[Int.ZERO_VALUE()].icon))
        weatherCallback.loadWind(wind = weather.wind.speed.getDoubleWithTwoDecimalsString())
        weatherCallback.loadTemperatures(
            minTemp = weather.temp.getTempMin(),
            maxTemp = weather.temp.getTempMax(),
            currentTemp = weather.temp.getTemp()
        )
    }

    private fun getImageUrl(stateImage: String): String {
        return BuildConfig.IMAGE_URL.replace(BuildConfig.IMAGE_REPLACE, stateImage)
    }

    interface WeatherCallback {
        fun loadTemperatures(minTemp: Int, maxTemp: Int, currentTemp: Int)
        fun loadCityName(cityName: String)
        fun loadWind(wind: String)
        fun loadImage(imageUrl: String)
    }
}