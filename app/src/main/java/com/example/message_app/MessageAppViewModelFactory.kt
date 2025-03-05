package com.example.message_app

import android.content.Context
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MessageAppViewModelFactory(
    private val repository: SharedPreferencesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessageAppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MessageAppViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}