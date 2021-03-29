package com.sharonahamon.petsleuth.models

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class PetLastSeenLocation(
    var petId: @RawValue MutableLiveData<Int>,
    var lastSeenDate: @RawValue MutableLiveData<String>,
    var street: @RawValue MutableLiveData<String>,
    var city: @RawValue MutableLiveData<String>,
    var state: @RawValue MutableLiveData<String>,
    var zip: @RawValue MutableLiveData<String>
) : Parcelable