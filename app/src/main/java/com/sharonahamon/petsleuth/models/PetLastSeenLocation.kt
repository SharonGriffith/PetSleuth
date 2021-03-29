package com.sharonahamon.petsleuth.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class PetLastSeenLocation(
    var petId: Int,
    var lastSeenDate: String,
    var street: String,
    var city: String,
    var state: String,
    var zip: String
) : Parcelable