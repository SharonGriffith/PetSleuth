package com.sharonahamon.petsleuth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sharonahamon.petsleuth.models.*
import timber.log.Timber

class PetSleuthViewModel : ViewModel() {
    var contactPerson = MutableLiveData<ContactPerson>()

    private var _pet = MutableLiveData<Pet>()
    var pet: LiveData<Pet>
        get() = _pet
        set(value) {
            _pet = value as MutableLiveData<Pet>
        }

    var petList: MutableList<LiveData<Pet>> = mutableListOf()

    init {
        Timber.i("ViewModel created")

        val newContactPerson = ContactPerson(
            MutableLiveData("sharon.a.hamon@gmail.com"),
            MutableLiveData("Sharon"),
            MutableLiveData("Hamon"),
            MutableLiveData(3036677720)
        )

        val newLastSeenLocation = PetLastSeenLocation(
            MutableLiveData(1),
            MutableLiveData("3/29/21"),
            MutableLiveData("Main Street"),
            MutableLiveData("Thornton"),
            MutableLiveData("CO"),
            MutableLiveData("80602")
        )

        val newPetSummary = PetSummary(
            MutableLiveData(1), MutableLiveData("Cat"),
            MutableLiveData(false), MutableLiveData(false), MutableLiveData("Lost")
        )

        val newPetDetail = PetDetail(
            MutableLiveData(1),
            MutableLiveData("orange tabby"),
            MutableLiveData("Mini"),
            MutableLiveData(true),
            MutableLiveData(newContactPerson),
            null
        )

        val newPet = Pet(
            MutableLiveData(1),
            MutableLiveData(newPetSummary),
            MutableLiveData(newPetDetail),
            MutableLiveData(newLastSeenLocation)
        )

        pet = MutableLiveData<Pet>(newPet)

        petList.add(pet)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel destroyed")
    }
}