package com.sharonahamon.petsleuth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class PetSleuthViewModel : ViewModel() {
    private var _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private var _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private var _species = MutableLiveData<String>()
    val species: LiveData<String>
        get() = _species

    private var _lastSeenDate = MutableLiveData<String>()
    val lastSeenDate: LiveData<String>
        get() = _lastSeenDate

    private var _city = MutableLiveData<String>()
    val city: LiveData<String>
        get() = _city

    private var _state = MutableLiveData<String>()
    val state: LiveData<String>
        get() = _state

    private var _zip = MutableLiveData<String>()
    val zip: MutableLiveData<String>
        get() = _zip

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    init {
        Timber.i("ViewModel created")
        loadMockData()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel destroyed")
    }

    fun loadMockData() {
        _email = MutableLiveData("sharon.a.hamon@gmail.com")
        _description = MutableLiveData("blue point siamese tortie")
        _species = MutableLiveData("Cat")
        _lastSeenDate = MutableLiveData("3/28/21")
        _city = MutableLiveData("Thornton")
        _state = MutableLiveData("CO")
        _zip = MutableLiveData("80602")
        _status = MutableLiveData("Lost")
    }
}