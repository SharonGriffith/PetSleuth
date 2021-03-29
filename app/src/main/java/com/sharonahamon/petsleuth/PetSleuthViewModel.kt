package com.sharonahamon.petsleuth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sharonahamon.petsleuth.models.*
import timber.log.Timber

class PetSleuthViewModel : ViewModel() {
    private var _appUser = MutableLiveData<AppUser>()
    val appUser : LiveData<AppUser>
    get() = _appUser

    private var _contactPerson = MutableLiveData<ContactPerson>()
    val contactPerson: LiveData<ContactPerson>
    get() = _contactPerson

    private var _petDetail = MutableLiveData<PetDetail>()
    val petDetail : LiveData<PetDetail>
    get() = _petDetail

    private var _petSummary = MutableLiveData<PetSummary>()
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