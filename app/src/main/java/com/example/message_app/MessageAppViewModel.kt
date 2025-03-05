package com.example.message_app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MessageAppViewModel(private val repository: SharedPreferencesRepository) : ViewModel() {

    // Método para guardar el número de teléfono y el mensaje en SharedPreferences
    fun saveData(phoneNumber: String, message: String) {
        viewModelScope.launch {
            repository.saveString("phone_number", phoneNumber)
            repository.saveString("message", message)
        }
    }

    // Método para leer e imprimir los valores guardados
    fun printSavedData() {
        viewModelScope.launch {
            val phoneNumber = repository.getPhoneNumber()
            val message = repository.getMessage()
            Log.d("SharedPreferences", "Número de teléfono guardado: $phoneNumber")
            Log.d("SharedPreferences", "Mensaje guardado: $message")
        }
    }
}