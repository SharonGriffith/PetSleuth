package com.sharonahamon.petsleuth.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PetSummary(
    var petId: Int,
    var species: String,
    var lastSeenLocation: PetLastSeenLocation,
    var isMine: Boolean,
    var isReunited: Boolean,
    var status: String
) : Parcelable