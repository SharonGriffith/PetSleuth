package com.sharonahamon.petsleuth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sharonahamon.petsleuth.models.*
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PetSleuthViewModel : ViewModel() {
    var loggedOnUserEmail = ""
    var loggedOnContactPerson: ContactPerson? = null

    private var _pet = MutableLiveData<Pet>()
    var pet: LiveData<Pet>
        get() = _pet
        set(value) {
            _pet = value as MutableLiveData<Pet>
        }

    var petList: MutableList<LiveData<Pet>> = mutableListOf()

    init {
        Timber.i("ViewModel created")

        Timber.i("pet list size=" + (petList?.size ?: 0))
    }

    fun buildDummyPetList() {
        Timber.i("begin buildDummyPetList()")

        savePetFromUserInputToViewModel(
            "mini@email.com",
            "Oklahoma City",
            "OK",
            "72129",
            "Lost",
            "Cat",
            "Female",
            "Orange Tabby",
            "12/18/92"
        )

        savePetFromUserInputToViewModel(
            "casper@email.com",
            "Thornton",
            "CO",
            "80602",
            "Found",
            "Cat",
            "Female",
            "Blue Point Siamese",
            "07/09/10"
        )

        savePetFromUserInputToViewModel(
            "zoe@email.com",
            "Glenwood",
            "CO",
            "12345",
            "Lost",
            "Cat",
            "Female",
            "Long Hair Tortie",
            "11/14/12"
        )

        savePetFromUserInputToViewModel(
            "gracie@email.com",
            "Thornton",
            "CO",
            "80234",
            "Found",
            "Cat",
            "Female",
            "Russian Blue",
            "11/01/18"
        )

        savePetFromUserInputToViewModel(
            "bella@email.com",
            "Thornton",
            "CO",
            "80602",
            "Found",
            "Dog",
            "Female",
            "German Shepherd",
            "01/01/21"
        )

        savePetFromUserInputToViewModel(
            "nikko@email.com",
            "Thornton",
            "CO",
            "80602",
            "Found",
            "Dog",
            "Male",
            "German Shepherd",
            "01/01/21"
        )

        loggedOnContactPerson = null

        Timber.i("end buildDummyPetList()")
    }

    fun savePetFromUserInputToViewModel(
        email: String,
        city: String,
        state: String,
        zip: String,
        status: String,
        species: String,
        sex: String,
        breed: String,
        date: String
    ) {
        var newPet = createPet(
            email,
            city,
            state,
            zip,
            species,
            status,
            breed,
            sex,
            date
        )

        // save the current pet
        savePet(newPet)

        // add the current pet to the list in viewModel
        petList?.add(MutableLiveData(newPet))

        Timber.i(
            "added the Pet object to the pet list, bringing the pet list size up to %s",
            petList?.size
        )
    }

    private fun createPet(
        email: String,
        city: String,
        state: String,
        zip: String,
        species: String,
        status: String,
        breed: String,
        sex: String,
        date: String
    ): Pet {
        val nextPetId = (petList?.size ?: 0) + 1
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
                    email,
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
        email: String,
        petId: Int,
        breed: String,
        sex: String
    ): PetDetail {
        val petDetail = PetDetail(
            MutableLiveData(petId),
            MutableLiveData(breed),
            null,
            MutableLiveData(false),
            MutableLiveData(createContactPerson(email)),
            null,
            MutableLiveData(sex)
        )

        Timber.i("created the PetDetail object")
        return petDetail
    }

    private fun createContactPerson(email: String): ContactPerson {
        if (loggedOnContactPerson == null || !loggedOnContactPerson!!.email.equals(email)) {
            Timber.i("created the ContactPerson object for email=%s", email)
            val newContactPerson = ContactPerson(MutableLiveData(email), null, null, null)
            loggedOnContactPerson = newContactPerson
        }

        Timber.i("returned the ContactPerson object")
        return loggedOnContactPerson as ContactPerson
    }

    private fun createPetLastSeenLocation(
        nextPetId: Int,
        city: String,
        state: String,
        zip: String,
        date: String
    ): PetLastSeenLocation {
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

    fun getToday(): String {
        val currentDateTime = LocalDateTime.now()
        val today = currentDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy"))

        Timber.i("today=%s", today)
        return today
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel destroyed")
    }

    fun logout() {
        // clear the active user out of the view model
        loggedOnUserEmail = ""
        loggedOnContactPerson = null

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

        Timber.i("called ViewModel logout()")
    }
}