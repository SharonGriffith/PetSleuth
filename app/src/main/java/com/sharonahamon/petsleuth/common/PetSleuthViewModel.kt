package com.sharonahamon.petsleuth.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.data.*
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PetSleuthViewModel : ViewModel() {
    private var _nextPetId = MutableLiveData(0)
    var nextPetId: LiveData<Int>
        get() = _nextPetId
        set(value) {
            _nextPetId = value as MutableLiveData<Int>
        }

    private var _currentUserEmail = MutableLiveData<String>("")
    val currentUserEmail: LiveData<String>
        get() = _currentUserEmail

    var loggedOnContactPerson: ContactPerson? = null
    var welcomeGreeting = MutableLiveData("")

    private var _requestedPetId = MutableLiveData<Int>()
    var requestedPetId: LiveData<Int>
        get() = _requestedPetId
        set(value) {
            _requestedPetId = value as MutableLiveData<Int>
        }

    // currently selected pet, for detail fragment
    private var _selectedPet = MutableLiveData<Pet>()

    var selectedPet: LiveData<Pet>
        get() = _selectedPet
        set(value) {
            _selectedPet = value as MutableLiveData<Pet>
        }

    // pet list, for list fragment
    var _petList: MutableList<LiveData<Pet>> = mutableListOf()
    var petList: List<LiveData<Pet>>
        get() = _petList
        set(value) {
            _petList = value.toMutableList()
        }

    // https://stackoverflow.com/questions/39427178/how-to-bind-method-on-radiogroup-on-checkchanged-event-with-data-binding/54261803#54261803

    // "Lost" or "Found"
    var instructions_which_status_radio_checked = MutableLiveData<Int>()

    // "Dog" or "Cat"
    var instructions_which_species_radio_checked = MutableLiveData<Int>()

    // "Male" or "Female"
    var instructions_which_sex_radio_checked = MutableLiveData<Int>()

    init {
        Timber.i("ViewModel created")
        Timber.i("pet list size=%s", _petList.size)

        // https://stackoverflow.com/questions/39427178/how-to-bind-method-on-radiogroup-on-checkchanged-event-with-data-binding/54261803#54261803

        // set the default value for the radio buttons on the instructions screen
        instructions_which_status_radio_checked.postValue(R.id.instructions_radio_status_lost) // "Lost"
        instructions_which_species_radio_checked.postValue(R.id.instructions_radio_species_dog) // "Dog"
        instructions_which_sex_radio_checked.postValue(R.id.instructions_radio_sex_male) // "Male"
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

    fun selectPet(item: Pet) {
        _selectedPet.value = item
        Timber.i("selected the Pet object for pet ID %s", item.petId.value.toString())
    }

    fun selectPet(petId: Int) {
        // update the current pet (used for the detail fragment) with a specific pet ID that the user picks
        Timber.i("current pet ID %s", selectedPet.value?.petId?.value.toString())
        Timber.i("requested pet ID %s", petId.toString())

        // make a copy of the input arg, since it can't be updated and we may need to override its value
        var requestedPetId = petId

        // prevent out of bounds error by not letting them pick an invalid value
        // default to the first pet if this happens
        if (petId > petList.size) {
            requestedPetId = 1
        }

        // if the currently selected pet is the same as the requested pet ID, do nothing
        if (selectedPet.value?.petId?.value?.equals(requestedPetId) == false) {
            Timber.i("starting the search")

            // else get the requested pet ID out of the list and load it into the current pet
            // so that it can be displayed in the detail screen
            val petListIterator = _petList.iterator()
            while (petListIterator.hasNext()) {
                val thisPet = petListIterator.next()
                Timber.i("looking at pet ID %s", thisPet.value?.petId?.value.toString())

                if (thisPet.value?.petId?.value?.equals(requestedPetId) == true) {
                    selectPet(thisPet.value!!)
                    Timber.i("pet ID " + requestedPetId + " found")
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
            date,
            null
        )

        // save the current pet
        selectPet(newPet)

        // add the current pet to the pet list
        addPetToList(newPet)

        // return the ID of the newly created pet (needed for the detail view)
        return newPet.petId.value!!
    }

    fun addPetToList(newPet: Pet): Int {
        _petList.add(MutableLiveData(newPet))

        Timber.i(
            "added the Pet object to the pet list, bringing the pet list size up to %s",
            _petList.size
        )

        return _petList.size
    }

    fun createPet(
        email: String,
        city: String,
        state: String,
        zip: String,
        species: String,
        status: String,
        breed: String,
        sex: String,
        date: String,
        petId: Int?
    ): Pet {
        var newPetId = petId ?: updateNextPetId()

        Timber.i("creating pet using ID %s", newPetId)

        // create the Pet object
        val pet = Pet(
            MutableLiveData(newPetId),
            // create the Pet Summary object
            MutableLiveData(
                createPetSummary(
                    _nextPetId.value!!,
                    species,
                    status
                )
            ),
            // create the Pet Detail object
            MutableLiveData(
                createPetDetail(
                    email,
                    _nextPetId.value!!,
                    breed,
                    sex
                )
            ),
            // create the Pet Last Seen object
            MutableLiveData(
                createPetLastSeenLocation(
                    _nextPetId.value!!,
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

    private fun updateNextPetId(): Int {
        _nextPetId = MutableLiveData(_petList.size + 1)
        return _nextPetId.value!!
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
        _nextPetId = MutableLiveData(0)

        clearWelcomeGreeting()

        // clear the active pet out of the view model
        selectedPet.value?.petSummary?.value?.petId ?: MutableLiveData(-1)
        selectedPet.value?.petSummary?.value?.species = MutableLiveData("")
        selectedPet.value?.petSummary?.value?.status ?: MutableLiveData("")

        selectedPet.value?.petDetail?.value?.petId ?: MutableLiveData(-1)
        selectedPet.value?.petDetail?.value?.breed ?: MutableLiveData("")

        selectedPet.value?.petLastSeenLocation?.value?.petId ?: MutableLiveData(-1)
        selectedPet.value?.petLastSeenLocation?.value?.city ?: MutableLiveData("")
        selectedPet.value?.petLastSeenLocation?.value?.state ?: MutableLiveData("")
        selectedPet.value?.petLastSeenLocation?.value?.zip ?: MutableLiveData("")

        // clear the dummy data out of the view model
        petList = emptyList()

        Timber.i("called ViewModel logout()")
    }

    private fun createWelcomeGreeting() {
        welcomeGreeting = if (_currentUserEmail.value?.isBlank() == true) {
            MutableLiveData("Hello!")
        } else MutableLiveData("Hello, ${_currentUserEmail.value.toString()}!")
    }

    private fun clearWelcomeGreeting() {
        welcomeGreeting = MutableLiveData("Hello!")
    }
}