package com.example.contactsapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.contactsapp.model.Contact
import com.example.contactsapp.repo.ContactsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateEditContactFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val _contact = MutableLiveData<Contact>()
    val contact: LiveData<Contact> get() = _contact

    fun saveContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            ContactsRepo.saveContact(getApplication(), contact)
        }
    }
    fun updateContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            ContactsRepo.updateContact(getApplication(), contact)
        }
    }
}