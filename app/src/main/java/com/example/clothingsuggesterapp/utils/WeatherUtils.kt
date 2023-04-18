package com.example.clothingsuggesterapp.utils

import com.example.clothingsuggesterapp.R

object WeatherUtils {

    private fun getRandomImageFromTemperature(temperature: Double, lastClothName: Int?): Int {
        val clothsWinter = listOf(
            R.drawable.winter1,
            R.drawable.winter2,
            R.drawable.winter3,
            R.drawable.winter4,
            R.drawable.winter5,
            R.drawable.winter6,
            R.drawable.winter7,
            R.drawable.winter8,
            R.drawable.winter9,
            R.drawable.winter10

        )
        val normalImages =
            listOf(R.drawable.normal1, R.drawable.normal2, R.drawable.normal3, R.drawable.normal4)
        val clothsSummer = listOf(
            R.drawable.summer1,
            R.drawable.summer2,
            R.drawable.summer3,
            R.drawable.summer4,
            R.drawable.summer5,
            R.drawable.summer6,
            R.drawable.summer7,
            R.drawable.summer8,
            R.drawable.summer9,
            R.drawable.summer10,
        )

        val images = when {
            temperature in 10.0..20.0 -> clothsWinter
            temperature in 21.0..24.0 -> normalImages
            else -> clothsSummer
        }

        val availableImages = if (lastClothName != null) {
            images.filter { it != lastClothName }
        } else {
            images
        }

        return if (availableImages.isEmpty()) {
            images.random()
        } else {
            availableImages.random()
        }
    }

    fun updateClothImage(
        currentTemperature: Double,
        prefsUtil: SharedPreferenceUtil
    ): Int {
        val lastClothId = prefsUtil.clothName?.toIntOrNull()
        val newImage = getRandomImageFromTemperature(currentTemperature, lastClothId)
        prefsUtil.clothName = newImage.toString()
        return newImage
    }

}