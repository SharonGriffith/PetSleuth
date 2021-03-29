package com.sharonahamon.petsleuth.models

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class PetSummary(
    var petId: @RawValue MutableLiveData<Int>,
    var species: @RawValue MutableLiveData<String>,
    var lastSeenLocation: @RawValue MutableLiveData<PetLastSeenLocation>,
    var isMine: @RawValue MutableLiveData<Boolean>,
    var isReunited: @RawValue MutableLiveData<Boolean>,
    var status: @RawValue MutableLiveData<String>
) : Parcelable