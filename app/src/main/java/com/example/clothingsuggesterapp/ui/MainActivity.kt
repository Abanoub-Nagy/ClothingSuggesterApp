package com.example.clothingsuggesterapp.ui

import android.os.Build
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.clothingsuggesterapp.databinding.ActivityMainBinding
import com.example.clothingsuggesterapp.model.WeatherResponse
import com.example.clothingsuggesterapp.presenter.IMainView
import com.example.clothingsuggesterapp.presenter.WeatherPresenter
import com.example.clothingsuggesterapp.ui.base.BaseActivity
import com.example.clothingsuggesterapp.utils.SharedPreferenceUtil
import com.example.clothingsuggesterapp.utils.WeatherUtils
import com.orhanobut.hawk.Hawk
import okio.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.ceil

class MainActivity : BaseActivity<ActivityMainBinding>(), IMainView {

    override val LOG_TAG: String? = MainActivity::class.simpleName
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate
    private val context = this


    override fun setup() {
        val weatherPresenter = WeatherPresenter(this)
        Hawk.init(this).build()
        weatherPresenter.getCurrentWeatherStatus()


    }

    override fun onGetCurrentWeatherStatusFailure(message: IOException) {
        runOnUiThread {
            Toast.makeText(context, message.message, Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onGetCurrentWeatherStatusSuccess(weatherResponse: WeatherResponse) {
        runOnUiThread {

            val dayTemperature = ceil(weatherResponse.main.temp)
            binding?.textViewWeatherTemperatureDegree?.text = "${dayTemperature.toInt()}°C"

            Glide.with(context)
                .load("https://openweathermap.org/img/wn/${weatherResponse.weather[0].icon}.png")
                .into(binding!!.imageViewStatuesWeather)
            val date = LocalDate
                .now()
                .format(DateTimeFormatter.ofPattern("dd MMMM YYYY"))
                .toString()

            binding!!.textViewDate.text = date

            val image = WeatherUtils.updateClothImage(dayTemperature, SharedPreferenceUtil)
            Glide.with(context).load(image).into(binding!!.imageViewClothingSuggestion)

            binding!!.textViewNextSuggestion.setOnClickListener {
                val image = WeatherUtils.updateClothImage(dayTemperature, SharedPreferenceUtil)
                Glide.with(context).load(image).into(binding!!.imageViewClothingSuggestion)
            }

            binding!!.textViewLocation.text = weatherResponse.name

        }
    }


}