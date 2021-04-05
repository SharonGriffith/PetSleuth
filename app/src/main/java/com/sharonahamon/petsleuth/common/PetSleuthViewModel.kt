package com.sharonahamon.petsleuth.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sharonahamon.petsleuth.data.*
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PetSleuthViewModel : ViewModel() {
    private var _currentUserEmail = MutableLiveData<String>("")
    val currentUserEmail: LiveData<String>
        get() = _currentUserEmail

    var loggedOnContactPerson: ContactPerson? = null
    var welcomeGreeting = MutableLiveData("")

    private var _currentPetId = MutableLiveData<Int>()
    var currentPetId: LiveData<Int>
        get() = _currentPetId
        set(value) {
            _currentPetId = value as MutableLiveData<Int>
        }

    // currently selected pet, for detail fragment
    private var _pet = MutableLiveData<Pet>()
    var pet: LiveData<Pet>
        get() = _pet
        set(value) {
            _pet = value as MutableLiveData<Pet>
        }

    // pet list, for list fragment
    private var _petList: MutableList<LiveData<Pet>> = mutableListOf()
    var petList: List<LiveData<Pet>>
        get() = _petList
        set(value) {
            _petList = value.toMutableList()
        }

    init {
        Timber.i("ViewModel created")
        Timber.i("pet list size=%s", _petList.size)
    }

    fun createWelcomeGreeting() {
        welcomeGreeting = if (_currentUserEmail.value?.isBlank() == true) {
            MutableLiveData("Hello!")
        } else MutableLiveData("Hello, ${_currentUserEmail.value.toString()}!")
    }

    fun buildDummyPetList() {
        Timber.i("begin buildDummyPetList()")

        addPet(
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

        addPet(
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

        addPet(
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

        addPet(
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

        addPet(
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

        addPet(
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

    fun loadPet(petId: Int) {
        // update the current pet (used for the detail fragment) with a specific pet ID that the user picks
        Timber.i("current pet ID %s", pet.value?.petId?.value.toString())
        Timber.i("requested pet ID %s", petId.toString())

        // make a copy of the input arg, since it can't be updated and we may need to override its value
        var requestedPetId = petId

        // prevent out of bounds error by not letting them pick an invalid value
        // default to the first pet if this happens
        if (petId > petList.size) {
            requestedPetId = 1
        }

        // if the currently selected pet is the same as the requested pet ID, do nothing
        if (pet.value?.petId?.value?.equals(requestedPetId) == false) {
            Timber.i("starting the search")

            // else get the requested pet ID out of the list and load it into the current pet
            // so that it can be displayed in the detail screen
            val petListIterator = _petList.iterator()
            while (petListIterator.hasNext()) {
                val thisPet = petListIterator.next()
                Timber.i("looking at pet ID %s", thisPet.value?.petId)

                if (thisPet.value?.petId?.value?.equals(requestedPetId) == true) {
                    pet = thisPet
                    Timber.i("pet ID " + pet.value?.petId?.value.toString() + " found")
                    return
                }
            }
        }
    }

    fun addPet(
        email: String,
        city: String,
        state: String,
        zip: String,
        status: String,
        species: String,
        sex: String,
        breed: String,
        date: String
    ): Int {
        val newPet = createPet(
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

        // add the current pet to the pet list
        _petList.add(MutableLiveData(newPet))

        Timber.i(
            "added the Pet object to the pet list, bringing the pet list size up to %s",
            _petList.size
        )

        // return the ID of the newly created pet (needed for the detail view)
        return _petList.size
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
        val nextPetId = _petList.size + 1
        Timber.i("the next pet ID to be used is %s", nextPetId)

        // create the Pet object
        val pet = Pet(
            MutableLiveData(nextPetId),
            // create the Pet Summary object
            MutableLiveData(
                createPetSummary(
                    nextPetId,
                    species,
                    status
                )
            ),
            // create the Pet Detail object
            MutableLiveData(
                createPetDetail(
                    email,
                    nextPetId,
                    breed,
                    sex
                )
            ),
            // create the Pet Last Seen object
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
        // only create a new Contact Person if the email differs from the current one
        // otherwise return the current one
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

    fun login(email: String) {
        _currentUserEmail = MutableLiveData(email)
        createWelcomeGreeting()
    }

    fun logout() {
        // clear the active user out of the view model
        _currentUserEmail = MutableLiveData("")
        loggedOnContactPerson = null

        this.createWelcomeGreeting()

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

    fun setCurrentUserEmail(emailFromUserInput: String) {
        MutableLiveData(emailFromUserInput).also { _currentUserEmail = it }
    }
}