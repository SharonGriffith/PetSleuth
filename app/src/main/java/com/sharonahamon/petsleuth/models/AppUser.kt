package com.sharonahamon.petsleuth.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppUser(
    var email: String, // serves as unique ID
    var password: String
) : Parcelable