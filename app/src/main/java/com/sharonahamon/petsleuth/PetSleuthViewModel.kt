package com.sharonahamon.petsleuth

import androidx.lifecycle.ViewModel
import com.sharonahamon.petsleuth.models.AppUser
import com.sharonahamon.petsleuth.models.ContactPerson
import com.sharonahamon.petsleuth.models.PetDetail
import com.sharonahamon.petsleuth.models.PetSummary
import timber.log.Timber

class PetSleuthViewModel : ViewModel() {
    lateinit var appUser: AppUser

    lateinit var contactPerson: ContactPerson

    lateinit var petDetail: PetDetail

    lateinit var petSummary: PetSummary

    init {
        Timber.i("ViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel destroyed")
    }
}