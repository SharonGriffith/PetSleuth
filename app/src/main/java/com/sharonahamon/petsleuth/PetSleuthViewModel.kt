package com.sharonahamon.petsleuth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sharonahamon.petsleuth.models.*
import timber.log.Timber

class PetSleuthViewModel : ViewModel() {
    private var _contactPerson = MutableLiveData<ContactPerson>()
    var contactPerson: LiveData<ContactPerson>
        get() = _contactPerson
        set(value) {
            _contactPerson = value as MutableLiveData<ContactPerson>
        }

    private var _pet = MutableLiveData<Pet>()
    var pet: LiveData<Pet>
        get() = _pet
        set(value) {
            _pet = value as MutableLiveData<Pet>
        }

    var petList: MutableList<LiveData<Pet>> = mutableListOf()

    init {
        Timber.i("ViewModel created")

        var petId = 1

        var newContactPerson = ContactPerson(
            MutableLiveData("moe.howard@user.com"),
            MutableLiveData("Moe"),
            MutableLiveData("Howard"),
            null
        )

        var newLastSeenLocation = PetLastSeenLocation(
            MutableLiveData(petId),
            MutableLiveData("01/01/21"),
            null,
            MutableLiveData("Lee's Summit"),
            MutableLiveData("MO"),
            MutableLiveData("12345")
        )

        var newPetSummary = PetSummary(
            MutableLiveData(petId),
            MutableLiveData("Cat"),
            null,
            null,
            MutableLiveData("Lost")
        )

        var newPetDetail = PetDetail(
            MutableLiveData(petId),
            MutableLiveData("Orange Tabby"),
            null,
            null,
            MutableLiveData(newContactPerson),
            null,
            MutableLiveData("Female")
        )

        var newPet = Pet(
            MutableLiveData(petId),
            MutableLiveData(newPetSummary),
            MutableLiveData(newPetDetail),
            MutableLiveData(newLastSeenLocation)
        )

        pet = MutableLiveData<Pet>(newPet)

        petList.add(pet)

        petId = 2

        newContactPerson = ContactPerson(
            MutableLiveData("larry.fine@user.com"),
            MutableLiveData("Larry"),
            MutableLiveData("Fine"),
            null
        )

        newLastSeenLocation = PetLastSeenLocation(
            MutableLiveData(petId),
            MutableLiveData("3/1/21"),
            null,
            MutableLiveData("Thornton"),
            MutableLiveData("CO"),
            MutableLiveData("80602")
        )

        newPetSummary = PetSummary(
            MutableLiveData(petId),
            MutableLiveData("Dog"),
            null,
            null,
            MutableLiveData("Found")
        )

        newPetDetail = PetDetail(
            MutableLiveData(petId),
            MutableLiveData("German Shepard"),
            null,
            null,
            MutableLiveData(newContactPerson),
            null,
            MutableLiveData("Female")
        )

        newPet = Pet(
            MutableLiveData(petId),
            MutableLiveData(newPetSummary),
            MutableLiveData(newPetDetail),
            MutableLiveData(newLastSeenLocation)
        )

        pet = MutableLiveData<Pet>(newPet)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel destroyed")
    }
}