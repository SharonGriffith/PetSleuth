package com.sharonahamon.petsleuth.data

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ContactPersonLD(
    var email: @RawValue MutableLiveData<String>, // serves as unique ID
    var firstName: @RawValue MutableLiveData<String>?,
    var lastName: @RawValue MutableLiveData<String>?,
    var phone: @RawValue MutableLiveData<Long>?
) : Parcelable

@Entity(tableName = "contact_person")
data class ContactPerson(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contact_person_id")
    var contactPersonID: Long,

    @ColumnInfo(name = "email")
    var email: String, // serves as unique ID

    @ColumnInfo(name = "first_name")
    var firstName: String,

    @ColumnInfo(name = "last_name")
    var lastName: String,

    @ColumnInfo(name = "phone")
    var phone: Long
)