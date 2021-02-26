package com.example.contactsapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.contactsapp.model.Address
import com.example.contactsapp.model.Contact
import com.example.contactsapp.repo.ContactsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlin.random.Random

class ContactsFragmentViewModel(application: Application) : AndroidViewModel(application) {
    /* old way of holding contacts
//    private val _contacts = MutableLiveData<List<Contact>?>()
//
//    val contacts: MutableLiveData<List<Contact>?> get() = _contacts */

    val contactsFlow = ContactsRepo.getContactsFlow(application.applicationContext).asLiveData(viewModelScope.coroutineContext)
/* old way of getting contacts without flow
//    fun getContacts() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val allContacts = ContactsRepo.getContacts(getApplication())
//            _contacts.postValue(allContacts)
//        }
//    } */

    /* used to test flow was working without leaving page
    fun addRandomContact(context: Context) {
        val contact = Contact(Random.nextInt(0,100).toString(), Random.nextInt(0, 100).toString(), Address("", "", "", 0), emptyList(), emptyList())
        viewModelScope.launch(Dispatchers.IO) {
            ContactsRepo.saveContact(context, contact)
        }
    } */

}