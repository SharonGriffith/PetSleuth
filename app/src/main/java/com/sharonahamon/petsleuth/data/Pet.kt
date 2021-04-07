package com.sharonahamon.petsleuth.data

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*

@Parcelize
data class PetLD(
    var petId: @RawValue MutableLiveData<Int>,
    var petSummary: @RawValue MutableLiveData<PetSummaryLD?>,
    var petDetail: @RawValue MutableLiveData<PetDetailLD>?,
    var petLastSeenLocation: @RawValue MutableLiveData<PetLastSeenLocationLD>?
) : Parcelable

@Parcelize
data class PetDetailLD(
    var petId: @RawValue MutableLiveData<Int>,
    var breed: @RawValue MutableLiveData<String?>,
    var petName: @RawValue MutableLiveData<String>?,
    var hasCollar: @RawValue MutableLiveData<Boolean>?,
    var contactPerson: @RawValue MutableLiveData<ContactPersonLD?>,
    var images: @RawValue MutableList<Any>? = mutableListOf(),
    var sex: @RawValue MutableLiveData<String>?
) : Parcelable

@Parcelize
data class PetSummaryLD(
    var petId: @RawValue MutableLiveData<Int>,
    var species: @RawValue MutableLiveData<String>?,
    var isMine: @RawValue MutableLiveData<Boolean>?,
    var isReunited: @RawValue MutableLiveData<Boolean>?,
    var status: @RawValue MutableLiveData<String?>
) : Parcelable

@Parcelize
data class PetLastSeenLocationLD(
    var petId: @RawValue MutableLiveData<Int>,
    var lastSeenDate: @RawValue MutableLiveData<String?>,
    var street: @RawValue MutableLiveData<String>?,
    var city: @RawValue MutableLiveData<String?>,
    var state: @RawValue MutableLiveData<String?>,
    var zip: @RawValue MutableLiveData<String?>
) : Parcelable

@Entity(tableName = "pet")
data class Pet(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pet_id")
    var petId: Long, // serves as unique ID

    @ColumnInfo(name = "pet_summary_id")
    var petSummaryId: Long, // foreign key

    @ColumnInfo(name = "pet_detail_id")
    var petDetailId: Long, // foreign key

    @ColumnInfo(name = "pet_last_seen_location_id")
    var petLastSeenLocationId: Long // foreign key
)

@Entity(tableName = "pet_detail")
data class PetDetail(
    @PrimaryKey(autoGenerate = true)
    var petSummaryId: Long, // serves as unique ID

    @ColumnInfo(name = "pet_id")
    var petId: Long, // foreign key

    @ColumnInfo(name = "breed")
    var breed: String,

    @ColumnInfo(name = "name")
    var petName: String,

    @ColumnInfo(name = "has_collar")
    var hasCollar: Boolean,

    @ColumnInfo(name = "contact_person_id")
    var contactPersonId: Long,

    @ColumnInfo(name = "sex")
    var sex: String
)

@Entity(tableName = "pet_summary")
data class PetSummary(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pet_summary_id")
    var petSummaryId: Long, // serves as unique ID

    @ColumnInfo(name = "pet_id")
    var petId: Long, // foreign key

    @ColumnInfo(name = "species")
    var species: String,

    @ColumnInfo(name = "status")
    var status: String
)

@Entity(tableName = "pet_last_seen_location")
data class PetLastSeenLocation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pet_last_seen_location_id")
    var petLastSeenLocationId: Long,

    @ColumnInfo(name = "pet_id")
    var petId: Long, // foreign key

    @ColumnInfo(name = "last_seen_date")
    var lastSeenDate: String,

    @ColumnInfo(name = "last_seen_street")
    var street: String,

    @ColumnInfo(name = "last_seen_city")
    var city: String,

    @ColumnInfo(name = "last_seen_state")
    var state: String,

    @ColumnInfo(name = "last_seen_zip")
    var zip: String
)