package com.sharonahamon.petsleuth.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PetDetail(
    var petId: Int,
    var description: String,
    var petName: String,
    var hasCollar: Boolean,
    var contactPerson: AppUser,
    var images: List<String>? = mutableListOf()
) : Parcelable