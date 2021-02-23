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
    suspend fun getContacts(context: Context) : List<Contact>? {
        val contactsDao = ContactsDatabase.getDatabase(context)?.contactsDao()
        return contactsDao?.getAllContacts()
    }

    fun saveContact(context: Context, contact: Contact) {
        val contactsDao = ContactsDatabase.getDatabase(context)?.contactsDao()
        contactsDao?.setContact(contact)
    }

    suspend fun updateContact(context: Context, contact: Contact) {
        val contactsDao = ContactsDatabase.getDatabase(context)?.contactsDao()
        contactsDao?.updateContact(contact)
    }
//    suspend fun getContactsFlow(context: Context) : Flow<List<Contact>>? {
//        val contactsDao = ContactsDatabase.getDatabase(context)?.contactsDao()
//        return contactsDao?.getAllContactsFlow()
//    }
//    suspend fun observerContacts() {
//        context?.let { context ->
//            ContactsRepo.getContactsFlow(context)?.collect { contactsFlow ->
//                if(contactsFlow.isNotEmpty()) {
//                    val listener: ContactClickListener = object : ContactClickListener {
//                        override fun itemClicked(contact: Contact) {
//                            val action = ContactsFragmentDirections.actionContactsFragmentToDetailContactFragment(contact)
//                            findNavController().navigate(action)
//                        }
//                    }
//                    viewModel.contacts.observe(this.viewLifecycleOwner, Observer {
//                        binding.rvContacts.layoutManager = LinearLayoutManager(this.context)
//                        binding.rvContacts.adapter = it?.let { contacts -> ContactAdapter(contacts, listener) }
//                    })
//                }
//            }
//        }
//    }
}