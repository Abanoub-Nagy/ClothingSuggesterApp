package com.example.clothingsuggesterapp.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceUtil {

    private var sharedPreferences: SharedPreferences? = null
    private const val SHARED_PREF = "ClothsPrefs"
    private val KEY = "clothes"

    fun initPrefUtil(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

    }

    var clothName: String?
        get() = sharedPreferences?.getString(KEY, null)
        set(value) {
            sharedPreferences?.edit()?.putString(KEY, value)?.apply()
        }

}