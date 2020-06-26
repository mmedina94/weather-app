package com.example.astropay.ui.viewmodels

import android.Manifest
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.astropay.base.BaseViewModel
import com.example.astropay.models.Weather
import com.example.astropay.repositories.OpenWeatherRepository
import com.example.astropay.utils.CheckPermissionUtil
import com.example.astropay.utils.LocationUtil
import kotlinx.coroutines.launch
import com.example.astropay.R
import com.example.astropay.models.citiesData
import com.example.astropay.utils.ZERO_VALUE

class MainViewModel constructor(
    private val openWeatherRepository: OpenWeatherRepository,
    private val locationUtil: LocationUtil,
    private val checkPermissionUtil: CheckPermissionUtil
) : BaseViewModel(), LocationUtil.LocationCallback {

    private val LOCATION_PERMISSION = Manifest.permission.ACCESS_COARSE_LOCATION

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather>
        get() = _weather

    private val _requestPermission = MutableLiveData<String>()
    val requestPermission: LiveData<String>
        get() = _requestPermission

    private val _cities = MutableLiveData<ArrayList<String>>()
    val cities: LiveData<ArrayList<String>>
        get() = _cities


    fun init() {
        loadCities()
        getWeatherByCityName(citiesData[Int.ZERO_VALUE()])
    }

    private fun loadCities() {
        viewModelScope.launch(uiScope) {
            _cities.value = citiesData
        }
    }

    fun getWeatherByCityName(cityName: String) {
        viewModelScope.launch {
            processResponse(
                response = openWeatherRepository.getWeatherByCityName(cityName),
                onSuccess = { showWeather(it) }
            )
        }
    }

    fun getWeatherForCurrentLocation() {
        checkPermissionUtil.checkPermission(
            permission = LOCATION_PERMISSION,
            requestPermission = this::requestPermission,
            permissionGranted = { locationUtil.getCurrentLocation(this) }
        )
    }

    fun userGrantedPermission() {
        locationUtil.getCurrentLocation(this)
    }

    fun userDeniedPermission() {
        showErrorMessage(message = R.string.error_message_permission)
    }

    private fun requestPermission() {
        viewModelScope.launch(uiScope) {
            _requestPermission.value = LOCATION_PERMISSION
        }
    }

    private fun showWeather(weather: Weather) {
        viewModelScope.launch(uiScope) {
            _weather.value = weather
        }
    }

    override fun currentLocation(location: Location) {
        viewModelScope.launch {
            processResponse(
                response = openWeatherRepository.getWeatherForCurrentLocation(
                    latitude = location.latitude,
                    longitude = location.longitude
                ),
                onSuccess = { showWeather(it) }
            )
        }
    }

    override fun locationError() {
        showErrorMessage()
    }

}