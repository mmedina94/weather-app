package com.example.astropay.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.astropay.R
import com.example.astropay.models.Weather
import kotlinx.android.synthetic.main.weather_view.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class WeatherView : LinearLayout, WeatherViewHandler.WeatherCallback, KoinComponent {

    private val weatherViewHandler: WeatherViewHandler by inject()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    init {
        LayoutInflater.from(context).inflate(R.layout.weather_view, this)
    }

    fun loadWeather(weather: Weather) {
        weatherViewHandler.init(weather = weather, weatherCallback = this)
    }

    override fun loadTemperatures(minTemp: Int, maxTemp: Int, currentTemp: Int) {
        textViewCurrentTemperature.text = String.format(
            context.getString(R.string.temperature),
            currentTemp
        )
        textViewTemperatureMin.text = String.format(
            context.getString(R.string.temperature_min),
            minTemp
        )
        textViewTemperatureMax.text = String.format(
            context.getString(R.string.temperature_max),
            maxTemp
        )
    }

    override fun loadWind(wind: String) {
        textViewWindSpeed.text = String.format(
            context.getString(R.string.wind_speed),
            wind
        )
    }

    override fun loadImage(imageUrl: String) {
        Glide.with(context)
            .load(imageUrl)
            .into(imageViewWeatherState)
    }

    override fun loadCityName(cityName: String) {
        textViewCityName.text = cityName
    }
}