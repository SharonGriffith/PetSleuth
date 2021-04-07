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

    @Insert
    suspend fun insertPetSummary(petSummary: PetSummary)

    @Insert
    suspend fun insertPetDetail(petDetail: PetDetail)

    @Insert
    suspend fun insertPetLastSeenLocation(petLastSeenLocation: PetLastSeenLocation)

    @Insert
    suspend fun insertContactPerson(contactPerson: ContactPerson)

    @Query("SELECT * FROM pet WHERE pet_id = :petId")
    fun getPet(petId: Long): LiveData<Pet>?

    @Query("SELECT * FROM pet_summary WHERE pet_id = :petId")
    fun getPetSummary(petId: Long): LiveData<PetSummary>?

    @Query("SELECT * FROM pet_detail WHERE pet_id = :petId")
    fun getPetDetail(petId: Long): LiveData<PetDetail>?

    @Query("SELECT * FROM pet_last_seen_location WHERE pet_id = :petId")
    fun getPetLastSeenLocation(petId: Long): LiveData<PetLastSeenLocation>?

    @Query("SELECT * FROM contact_person WHERE contact_person_id = :contactPersonId")
    fun getContactPerson(contactPersonId: Long): LiveData<ContactPerson>?

    @Query("SELECT * FROM pet")
    fun getAllPets(): LiveData<List<Pet>>?

    @Update
    suspend fun updatePet(pet: Pet)

    @Update
    suspend fun updatePetSummary(petSummary: PetSummary)

    @Update
    suspend fun udpatePetDetail(petDetail: PetDetail)

    @Update
    suspend fun updatePetLastSeenLocation(petLastSeenLocation: PetLastSeenLocation)

    @Update
    suspend fun updateContactPerson(contactPerson: ContactPerson)

    suspend fun clear() {
        clearPet()
        clearPetDetail()
        clearPetLastSeenLocation()
        clearPetSummary()
        clearContactPerson()
    }

    @Query("DELETE FROM pet")
    suspend fun clearPet()

    @Query("DELETE FROM pet_summary")
    suspend fun clearPetSummary()

    @Query("DELETE FROM pet_detail")
    suspend fun clearPetDetail()

    @Query("DELETE FROM pet_last_seen_location")
    suspend fun clearPetLastSeenLocation()

    @Query("DELETE FROM contact_person")
    suspend fun clearContactPerson()
}