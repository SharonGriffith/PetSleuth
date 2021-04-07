package com.sharonahamon.petsleuth.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PetSummaryDao {
    @Insert
    suspend fun insertPetSummary(petSummary: PetSummary)

    @Query("SELECT * FROM pet_summary WHERE pet_id = :petId")
    fun getPetSummary(petId: Long): LiveData<PetSummary>?

    @Update
    suspend fun updatePetSummary(petSummary: PetSummary)

    @Query("DELETE FROM pet_summary")
    suspend fun clear()
}