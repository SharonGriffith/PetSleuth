package com.sharonahamon.petsleuth.models

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class PetDetail(
    var petId: @RawValue MutableLiveData<Int>,
    var description: @RawValue MutableLiveData<String>,
    var petName: @RawValue MutableLiveData<String>,
    var hasCollar: @RawValue MutableLiveData<Boolean>,
    var contactPerson: @RawValue MutableLiveData<AppUser>,
    var images: List<String>? = mutableListOf()
) : Parcelable