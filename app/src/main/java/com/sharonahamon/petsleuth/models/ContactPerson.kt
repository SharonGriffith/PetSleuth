package com.sharonahamon.petsleuth.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactPerson(
    var email: String, // serves as unique ID
    var firstName: String,
    var lastName: String,
    var phone: Long
) : Parcelable