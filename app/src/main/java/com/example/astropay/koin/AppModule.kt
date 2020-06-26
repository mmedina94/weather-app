package com.example.astropay.koin

import com.example.astropay.repositories.OpenWeatherRepository
import com.example.astropay.repositories.OpenWeatherRepositoryImpl
import com.example.astropay.ui.adapters.CityAdapter
import com.example.astropay.ui.customview.WeatherViewHandler
import com.example.astropay.ui.viewmodels.MainViewModel
import com.example.astropay.utils.CheckPermissionUtil
import com.example.astropay.utils.LocationUtil
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<OpenWeatherRepository> { OpenWeatherRepositoryImpl(get()) }
    factory { LocationUtil(get()) }
    factory { CheckPermissionUtil(get()) }
    factory { CityAdapter() }
    factory { WeatherViewHandler() }

    viewModel {
        MainViewModel(
            get(),
            get(),
            get()
        )
    }
}