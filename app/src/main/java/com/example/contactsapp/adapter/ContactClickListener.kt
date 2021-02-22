package com.example.contactsapp.adapter

import com.example.contactsapp.model.Contact

interface ContactClickListener {
    fun itemClicked(contact: Contact)
}