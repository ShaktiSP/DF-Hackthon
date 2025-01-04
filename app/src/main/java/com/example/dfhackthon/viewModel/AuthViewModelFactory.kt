package com.example.dfhackthon.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dfhackthon.network.AuthRepository

class AuthViewModelFactory(
    private val application: Application,
    private val repository: AuthRepository
) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                return RegisterViewModel(application, repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}
