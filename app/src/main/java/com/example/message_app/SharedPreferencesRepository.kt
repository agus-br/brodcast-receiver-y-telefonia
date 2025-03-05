package com.example.message_app

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesRepository(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("sms_prefs", Context.MODE_PRIVATE)

    // Guardar un valor en SharedPreferences
    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    // Leer un valor de SharedPreferences
    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    // Leer el número de teléfono guardado
    fun getPhoneNumber(): String {
        return getString("phone_number", "")
    }

    // Leer el mensaje guardado
    fun getMessage(): String {
        return getString("message", "")
    }
}