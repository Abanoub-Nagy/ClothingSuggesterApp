package com.example.clothingsuggesterapp.data

import com.example.clothingsuggesterapp.model.WeatherResponse
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class WeatherServiceImpl : WeatherService {
    private val client = OkHttpClient()
    private val gson = Gson()


    override fun onSuccess(
        onGetCurrentWeatherStatusSuccess: (weatherResponse: WeatherResponse) -> Unit,
        onGetCurrentWeatherStatusFailure: (e: IOException) -> Unit
    ) {
        val request = Request.Builder().url(Companion.baseUrl).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onGetCurrentWeatherStatusFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseString = response.body?.string()
                val weatherResponse = gson.fromJson(responseString, WeatherResponse::class.java)
                onGetCurrentWeatherStatusSuccess(weatherResponse)
            }

        })
    }

    companion object {
        private const val baseUrl =
            "https://api.openweathermap.org/data/2.5/weather?q=Assiut&appid=e310ce954e51f1aee6146312081e1aac&units=metric"
    }

}