package com.example.contactsapp.repo

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.adapter.ContactAdapter
import com.example.contactsapp.adapter.ContactClickListener
import com.example.contactsapp.model.Contact
import com.example.contactsapp.repo.local.ContactsDatabase
import com.example.contactsapp.view.ContactsFragmentDirections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

object ContactsRepo {
    /* old way of getting contacts without flow
//    suspend fun getContacts(context: Context) : List<Contact>? {
//        val contactsDao = ContactsDatabase.getDatabase(context).contactsDao()
//        return contactsDao.getAllContacts()
//    } */

    fun getContactsFlow(context: Context) = ContactsDatabase.getDatabase(context)
        .contactsDao().getAllContactsFlow()

    fun saveContact(context: Context, contact: Contact) {
        val contactsDao = ContactsDatabase.getDatabase(context).contactsDao()
        contactsDao.setContact(contact)
    }

    suspend fun updateContact(context: Context, contact: Contact) {
        val contactsDao = ContactsDatabase.getDatabase(context).contactsDao()
        contactsDao.updateContact(contact)
    }
}