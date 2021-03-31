package com.sharonahamon.petsleuth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sharonahamon.petsleuth.models.*
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

        saveDataFromUserInputToViewModel(
            "Lee's Summit",
            "MO",
            "12345",
            "Lost",
            "Cat",
            "Female",
            "Orange Tabby"
        )

        saveDataFromUserInputToViewModel(
            "Thornton",
            "CO",
            "80602",
            "Found",
            "Dog",
            "Female",
            "German Shepherd"
        )

        Timber.i("list size=" + petList.size)
    }

    fun saveDataFromUserInputToViewModel(
        city: String,
        state: String,
        zip: String,
        status: String,
        species: String,
        sex: String,
        breed: String
    ) {
        var newPet = createPet(
            city,
            state,
            zip,
            species,
            status,
            breed,
            sex,
            getToday()
        )

        // TODO: link to ContactPerson

        savePet(newPet)

        // add the current pet to the list in viewModel
        Timber.i("saved the Pet object in the list")
        petList.add(MutableLiveData(newPet))
    }

    private fun createPet(
        city: String,
        state: String,
        zip: String,
        species: String,
        status: String,
        breed: String,
        sex: String,
        date: String
    ): Pet {
        val nextPetId = petList.size + 1
        Timber.i("the next pet ID to be used is %s", nextPetId)

        // create the Pet object for the first time
        val pet = Pet(
            MutableLiveData(nextPetId),
            MutableLiveData(
                createPetSummary(
                    nextPetId,
                    species,
                    status
                )
            ),
            MutableLiveData(
                createPetDetail(
                    nextPetId,
                    breed,
                    sex
                )
            ),
            MutableLiveData(
                createPetLastSeenLocation(
                    nextPetId,
                    city,
                    state,
                    zip,
                    date
                )
            )
        )
        Timber.i("created the Pet object")

        return pet
    }

    private fun savePet(pet: Pet) {
        this.pet = MutableLiveData(pet)
        Timber.i("saved the Pet object")
    }

    private fun createPetSummary(petId: Int, species: String, status: String): PetSummary {
        val petSummary = PetSummary(
            MutableLiveData(petId),
            MutableLiveData(species),
            null,
            null,
            MutableLiveData(status)
        )

        Timber.i("created the PetSummary object")
        return petSummary
    }

    private fun createPetDetail(
        petId: Int,
        breed: String,
        sex: String
    ): PetDetail {
        val petDetail = PetDetail(
            MutableLiveData(petId),
            MutableLiveData(breed),
            null,
            MutableLiveData(false),
            null,
            null,
            MutableLiveData(sex)
        )

        Timber.i("created the PetDetail object")
        return petDetail
    }

    private fun createPetLastSeenLocation(
        nextPetId: Int,
        city: String,
        state: String,
        zip: String,
        date: String
    ): PetLastSeenLocation {
        // create the PetLastSeenLocation object for the first time
        val petLastSeenLocation = PetLastSeenLocation(
            MutableLiveData(nextPetId),
            MutableLiveData(date),
            null,
            MutableLiveData(city),
            MutableLiveData(state),
            MutableLiveData(zip)
        )

        Timber.i("created the PetLastSeenLocation object")
        return petLastSeenLocation
    }

    private fun getToday(): String {
        val currentDateTime = LocalDateTime.now()
        val today = currentDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy"))

        Timber.i("today= %s", today)
        return today
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel destroyed")
    }

    fun logout() {
        // clear the active user out of the view model
        contactPerson.value?.email = MutableLiveData("")

        // clear the active pet out of the view model
        pet.value?.petSummary?.value?.petId ?: MutableLiveData(-1)
        pet.value?.petSummary?.value?.species = MutableLiveData("")
        pet.value?.petSummary?.value?.status ?: MutableLiveData("")

        pet.value?.petDetail?.value?.petId ?: MutableLiveData(-1)
        pet.value?.petDetail?.value?.breed ?: MutableLiveData("")

        pet.value?.petLastSeenLocation?.value?.petId ?: MutableLiveData(-1)
        pet.value?.petLastSeenLocation?.value?.city ?: MutableLiveData("")
        pet.value?.petLastSeenLocation?.value?.state ?: MutableLiveData("")
        pet.value?.petLastSeenLocation?.value?.zip ?: MutableLiveData("")
    }
}