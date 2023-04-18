package com.example.clothingsuggesterapp.presenter

import com.example.clothingsuggesterapp.model.WeatherResponse
import okio.IOException

interface IMainView {
    fun onGetCurrentWeatherStatusFailure(message: IOException)
    fun onGetCurrentWeatherStatusSuccess(weatherResponse: WeatherResponse)
}