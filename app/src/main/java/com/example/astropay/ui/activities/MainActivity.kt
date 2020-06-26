package com.example.astropay.ui.activities

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astropay.R
import com.example.astropay.ui.adapters.CityAdapter
import com.example.astropay.ui.viewmodels.MainViewModel
import com.example.astropay.utils.ZERO_VALUE
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivity : AppCompatActivity(), KoinComponent {

    private val REQUEST_PERMISION_CODE = 301
    private val mainViewModel: MainViewModel by viewModel()
    private val cityAdapter: CityAdapter by inject()

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        bindView()
    }

    @SuppressLint("NewApi")
    private fun initViewModel() {
        mainViewModel.init()


        mainViewModel.weather.observe(this, Observer {
            weatherView?.loadWeather(weather = it)
        })

        mainViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        mainViewModel.requestPermission.observe(this, Observer {
            requestPermissions(arrayOf(it), REQUEST_PERMISION_CODE)
        })

        mainViewModel.cities.observe(this, Observer {
            cityAdapter.setData(
                data = it,
                onClick = { cityName ->
                    mainViewModel.getWeatherByCityName(cityName)
                }
            )
            recyclerViewCities.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = cityAdapter
            }
        })
    }

    private fun bindView() {
        textViewGetWeatherByLocation?.setOnClickListener {
            mainViewModel.getWeatherForCurrentLocation()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_PERMISION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[Int.ZERO_VALUE()] == PackageManager.PERMISSION_GRANTED) {
                    mainViewModel.userGrantedPermission()
                } else {
                    mainViewModel.userDeniedPermission()
                }
            }
        }
    }

}
