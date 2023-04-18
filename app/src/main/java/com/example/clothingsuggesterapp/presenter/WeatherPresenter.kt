package com.example.clothingsuggesterapp.presenter

import com.example.clothingsuggesterapp.data.WeatherServiceImpl
import com.example.clothingsuggesterapp.model.WeatherResponse
import okio.IOException

class WeatherPresenter(private var iMainView: IMainView) {
    private val weatherServiceImpl = WeatherServiceImpl()

    fun getCurrentWeatherStatus() =
        weatherServiceImpl.onSuccess(
            ::onGetCurrentWeatherStatusSuccess,
            ::onGetCurrentWeatherStatusFailure
        )

    private fun onGetCurrentWeatherStatusSuccess(weatherResponse: WeatherResponse) =
        iMainView.onGetCurrentWeatherStatusSuccess(weatherResponse)

    private fun onGetCurrentWeatherStatusFailure(message: IOException) =
        iMainView.onGetCurrentWeatherStatusFailure(message)
}