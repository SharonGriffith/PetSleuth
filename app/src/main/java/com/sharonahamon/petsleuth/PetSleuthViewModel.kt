package com.sharonahamon.petsleuth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sharonahamon.petsleuth.models.*
import timber.log.Timber

class PetSleuthViewModel : ViewModel() {
    private lateinit var _appUser: MutableLiveData<AppUser>
    val appUser: LiveData<AppUser>
        get() = _appUser

    private lateinit var _contactPerson: MutableLiveData<ContactPerson>
    val contactPerson: LiveData<ContactPerson>
        get() = _contactPerson

    private lateinit var _petLastSeenLocation: MutableLiveData<PetLastSeenLocation>
    val petLastSeenLocation: LiveData<PetLastSeenLocation>
        get() = _petLastSeenLocation

    private lateinit var _petDetail: MutableLiveData<PetDetail>
    val petDetail: LiveData<PetDetail>
        get() = _petDetail

    private lateinit var _petSummary: MutableLiveData<PetSummary>
    val petSummary: LiveData<PetSummary>
        get() = _petSummary

    init {
        Timber.i("ViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel destroyed")
    }
}