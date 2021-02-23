package com.example.contactsapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.contactsapp.model.Contact
import com.example.contactsapp.repo.ContactsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class ContactsFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val _contacts = MutableLiveData<List<Contact>?>()

    val contacts: MutableLiveData<List<Contact>?> get() = _contacts

    fun getContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val allContacts = ContactsRepo.getContacts(getApplication())
            _contacts.postValue(allContacts)
        }
    }

}