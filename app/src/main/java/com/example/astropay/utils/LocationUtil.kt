package com.example.astropay.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationUtil constructor(private val context: Context) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun getCurrentLocation(callback: LocationCallback) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                callback.currentLocation(it)
            } ?: callback.locationError()
        }
    }


    interface LocationCallback {
        fun currentLocation(location: Location)
        fun locationError()
    }
}