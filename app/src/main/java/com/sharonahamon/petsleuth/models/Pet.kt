package com.sharonahamon.petsleuth.models

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Pet(
    var petId: @RawValue MutableLiveData<Int>,
    var petSummary: @RawValue MutableLiveData<PetSummary?>,
    var petDetail: @RawValue MutableLiveData<PetDetail>?,
    var petLastSeenLocation: @RawValue MutableLiveData<PetLastSeenLocation>?
) : Parcelable

@Parcelize
data class PetDetail(
    var petId: @RawValue MutableLiveData<Int>,
    var breed: @RawValue MutableLiveData<String?>,
    var petName: @RawValue MutableLiveData<String>?,
    var hasCollar: @RawValue MutableLiveData<Boolean>?,
    var contactPerson: @RawValue MutableLiveData<ContactPerson>,
    var images: @RawValue MutableList<Any>? = mutableListOf(),
    var sex: @RawValue MutableLiveData<String>?
) : Parcelable

@Parcelize
data class PetLastSeenLocation(
    var petId: @RawValue MutableLiveData<Int>,
    var lastSeenDate: @RawValue MutableLiveData<String?>,
    var street: @RawValue MutableLiveData<String>?,
    var city: @RawValue MutableLiveData<String?>,
    var state: @RawValue MutableLiveData<String?>,
    var zip: @RawValue MutableLiveData<String?>
) : Parcelable

@Parcelize
data class PetSummary(
    var petId: @RawValue MutableLiveData<Int>,
    var species: @RawValue MutableLiveData<String>?,
    var isMine: @RawValue MutableLiveData<Boolean>?,
    var isReunited: @RawValue MutableLiveData<Boolean>?,
    var status: @RawValue MutableLiveData<String?>
) : Parcelable