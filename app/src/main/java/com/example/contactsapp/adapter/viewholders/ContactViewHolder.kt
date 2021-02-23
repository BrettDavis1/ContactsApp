package com.example.contactsapp.adapter.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.adapter.ContactClickListener
import com.example.contactsapp.databinding.ContactItemBinding
import com.example.contactsapp.model.Contact

class ContactViewHolder(
    private val binding: ContactItemBinding,
    val listener: ContactClickListener)
    :RecyclerView.ViewHolder(binding.root) {
    fun loadContact(contact: Contact) = with(binding) {
        binding.root.setOnClickListener{
            listener.itemClicked(contact)
        }
        val firstNameInitial: String = contact.fName[0].toString()
        val lastNameInitial: String = contact.lName[0].toString()
        val nameInitials: String = firstNameInitial + lastNameInitial
        binding.btnInitials.text = nameInitials
        val name = "${contact.fName} ${contact.lName}"
        binding.tvName.text = name
    }
}