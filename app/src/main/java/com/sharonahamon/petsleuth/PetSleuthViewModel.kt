package com.sharonahamon.petsleuth

import androidx.lifecycle.ViewModel
import com.sharonahamon.petsleuth.models.*
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

    fun loadMockData() {
        appUser = AppUser("sharon.a.hamon@gmail.com")
        contactPerson =
            ContactPerson("sharon.a.hamon@gmail.com", "Sharon", "Hamon", 3036677720)
        petDetail =
            PetDetail(1, "blue point siamese tortie", "Casper", true, appUser, null)
        petSummary = PetSummary(
            1,
            "Cat",
            PetLastSeenLocation(1, "3/28/21", "Main Street", "Thornton", "CO", "80602"),
            isMine = true,
            isReunited = false,
            status = "Lost"
        )
    }
}