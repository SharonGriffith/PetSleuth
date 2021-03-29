package com.sharonahamon.petsleuth.models

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class AppUser(
    var email: @RawValue MutableLiveData<String> // serves as unique ID
) : Parcelable