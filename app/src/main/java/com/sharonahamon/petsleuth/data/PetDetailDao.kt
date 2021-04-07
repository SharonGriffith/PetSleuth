package com.sharonahamon.petsleuth.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PetDetailDao {
    @Insert
    suspend fun insertPetDetail(petDetail: PetDetail)

    @Query("SELECT * FROM pet_detail WHERE pet_id = :petId")
    fun getPetDetail(petId: Long): LiveData<PetDetail>?

    @Update
    suspend fun udpatePetDetail(petDetail: PetDetail)

    @Query("DELETE FROM pet_detail")
    suspend fun clear()
}