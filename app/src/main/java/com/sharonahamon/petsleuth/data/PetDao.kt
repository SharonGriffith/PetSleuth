package com.sharonahamon.petsleuth.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PetDao {
    @Insert
    suspend fun insertPet(pet: Pet)

    @Query("SELECT * FROM pet WHERE pet_id = :petId")
    fun getPet(petId: Long): LiveData<Pet>?

    @Query("SELECT * FROM pet")
    fun getAllPets(): LiveData<List<Pet>>?

    @Update
    suspend fun updatePet(pet: Pet)

    @Query("DELETE FROM pet")
    suspend fun clear()
}