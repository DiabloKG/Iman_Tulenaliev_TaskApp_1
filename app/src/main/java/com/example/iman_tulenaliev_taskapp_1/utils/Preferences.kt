package com.example.iman_tulenaliev_taskapp_1.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.iman_tulenaliev_taskapp_1.R

class Preferences(context: Context) {
    val sharedPreference: SharedPreferences =  context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    var board: Boolean
    get () = sharedPreference.getBoolean("board", false)
    set (value) = sharedPreference.edit().putBoolean("board", value).apply()

    var imageProfile: String?
    get() = sharedPreference.getString("img", R.mipmap.ic_launcher.toString())
    set(value) = sharedPreference.edit().putString("img", value).apply()

    var nameProfle: String?
        get() = sharedPreference.getString("name", "")
        set(value) = sharedPreference.edit().putString("name", value).apply()
}