package com.example.contactsapp.repo

import android.content.Context
import com.example.contactsapp.model.Contact
import com.example.contactsapp.repo.local.ContactsDatabase

object ContactsRepo {
    suspend fun getContacts(context: Context) : List<Contact>? {
        val contactsDao = ContactsDatabase.getDatabase(context)?.contactsDao()
        return contactsDao?.getAllContacts()
    }

    suspend fun saveContact(context: Context, contact: Contact) {
        val contactsDao = ContactsDatabase.getDatabase(context)?.contactsDao()
        contactsDao?.setContact(contact)
    }

    suspend fun updateContact(context: Context, contact: Contact) {
        val contactsDao = ContactsDatabase.getDatabase(context)?.contactsDao()
        contactsDao?.updateContact(contact)
    }
}