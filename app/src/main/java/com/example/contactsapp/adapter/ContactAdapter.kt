package com.example.contactsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactsapp.model.Contact
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.databinding.ContactItemBinding

class ContactAdapter(val contacts: List<Contact>, private val listener: ContactClickListener):
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding: ContactItemBinding = ContactItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContactViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.setContact(contacts[position], listener)
    }

    class ContactViewHolder(val binding: ContactItemBinding, val listener: ContactClickListener) :RecyclerView.ViewHolder(binding.root) {
        fun setContact(contact: Contact, listener: ContactClickListener) {
            binding.root.setOnClickListener(View.OnClickListener {
                listener.itemClicked(contact)
            })
            val firstNameInitial: String = contact.fName[0].toString()
            val lastNameInitial: String = contact.lName[0].toString()
            val nameInitials: String = firstNameInitial + lastNameInitial
            binding.btnInitials.text = nameInitials
            val name = "${contact.fName} ${contact.lName}"
            binding.tvName.text = name
        }
    }
}