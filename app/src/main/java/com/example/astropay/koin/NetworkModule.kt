package com.example.astropay.koin

import com.example.astropay.BuildConfig
import com.example.astropay.services.OpenWeatherMapApi
import com.example.astropay.network.ServiceInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { ServiceInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideOpenWeatherMapApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}


fun provideOkHttpClient(
    serviceInterceptor: ServiceInterceptor
): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(serviceInterceptor)
        .build()
}

fun provideOpenWeatherMapApi(retrofit: Retrofit): OpenWeatherMapApi =
    retrofit.create(OpenWeatherMapApi::class.java)