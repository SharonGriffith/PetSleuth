package com.sharonahamon.petsleuth.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactPersonDao {
    @Insert
    suspend fun insertContactPerson(contactPerson: ContactPerson)

    @Query("SELECT * FROM contact_person WHERE contact_person_id = :contactPersonId")
    fun getContactPerson(contactPersonId: Long): LiveData<ContactPerson>?

    @Update
    suspend fun updateContactPerson(contactPerson: ContactPerson)

    @Query("DELETE FROM contact_person")
    suspend fun clear()
}