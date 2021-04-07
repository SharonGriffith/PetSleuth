package com.sharonahamon.petsleuth.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PetLastSeenLocationDao {
    @Insert
    suspend fun insertPetLastSeenLocation(petLastSeenLocation: PetLastSeenLocation)

    @Query("SELECT * FROM pet_last_seen_location WHERE pet_id = :petId")
    fun getPetLastSeenLocation(petId: Long): LiveData<PetLastSeenLocation>?

    @Update
    suspend fun updatePetLastSeenLocation(petLastSeenLocation: PetLastSeenLocation)

    @Query("DELETE FROM pet_last_seen_location")
    suspend fun clear()
}