package com.example.clothingsuggesterapp.data

import com.example.clothingsuggesterapp.model.WeatherResponse
import okio.IOException

interface WeatherService {
    fun onSuccess(
        onGetCurrentWeatherStatusSuccess: (weatherResponse: WeatherResponse) -> Unit,
        onGetCurrentWeatherStatusFailure: (e: IOException) -> Unit
    )
}