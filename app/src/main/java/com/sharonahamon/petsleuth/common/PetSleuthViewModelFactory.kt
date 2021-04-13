package com.sharonahamon.petsleuth.common

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sharonahamon.petsleuth.data.PetDao

/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the Dao and context to the ViewModel.
 */
class PetSleuthViewModelFactory(
    private val dataSource: PetDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetSleuthViewModel::class.java)) {
            return PetSleuthViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
