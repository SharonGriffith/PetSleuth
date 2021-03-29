package com.sharonahamon.petsleuth

import androidx.lifecycle.ViewModel
import timber.log.Timber

class PetSleuthViewModel : ViewModel() {
    init {
        Timber.i("ViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel destroyed")
    }
}