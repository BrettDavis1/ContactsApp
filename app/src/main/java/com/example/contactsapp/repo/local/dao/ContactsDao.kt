package com.example.contactsapp.repo.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.contactsapp.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setContact(contact: Contact)

    @Query("SELECT * FROM contacts WHERE id = :id")
    suspend fun findContactById(id: Long) : Contact

    @Query("SELECT * FROM contacts ORDER BY fName ASC")
    suspend fun getAllContacts(): List<Contact>

    @Query("SELECT * FROM contacts ORDER BY fName ASC")
    fun getAllContactsFlow(): Flow<List<Contact>>

    @get:Query("SELECT * FROM contacts ORDER BY fName ASC")
    val contacts: LiveData<List<Contact>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateContact(contact: Contact)
}