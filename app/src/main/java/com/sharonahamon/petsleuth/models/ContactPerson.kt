package com.sharonahamon.petsleuth.models

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ContactPerson(
    var email: @RawValue MutableLiveData<String>, // serves as unique ID
    var firstName: @RawValue MutableLiveData<String>,
    var lastName: @RawValue MutableLiveData<String>,
    var phone: @RawValue MutableLiveData<Long>
) : Parcelable