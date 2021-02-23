package com.example.contactsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactsapp.model.Contact
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
import com.example.contactsapp.databinding.ContactItemBinding
import com.example.contactsapp.databinding.RvHeaderBinding

class ContactAdapter(val contacts: List<Contact>, private val listener: ContactClickListener):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {//ContactAdapter.ContactViewHolder>(){
    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

//    fun getViewType(contacts: List<Contact>) {
//        var count = 0
//        var headerString = ""
//        for(contact in contacts) {
//            if(contact.fName[0].toUpperCase().toString() == headerString) {
//                count++
//            }
//        }
//    }
    override fun getItemViewType(position: Int): Int {
        //method to get positions of all new letters
        return if(position == 0) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {//ContactViewHolder {
        return if(viewType == TYPE_HEADER) {
            val binding: RvHeaderBinding = RvHeaderBinding.inflate(
                LayoutInflater.from(parent.context),parent, false
            )
            HeaderViewHolder(binding)
        } else {
            val binding: ContactItemBinding = ContactItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            ContactViewHolder(binding, listener)
        }
    }

    override fun getItemCount(): Int {
        // needs to account for how many headers are added
        if(contacts.size == 0) {
            return 0
        }
        return contacts.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is HeaderViewHolder) {
            holder.setHeader(contacts[position])
        }
        if(holder is ContactViewHolder) {
            holder.setContact(contacts[position-1], listener)
        }
    }
    class ContactViewHolder(val binding: ContactItemBinding, val listener: ContactClickListener) :RecyclerView.ViewHolder(binding.root) {
        fun setContact(contact: Contact, listener: ContactClickListener) {
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
    class HeaderViewHolder(val binding: RvHeaderBinding) :RecyclerView.ViewHolder(binding.root) {
        fun setHeader(contact: Contact) {
            binding.tvHeader.text = contact.fName[0].toString()
        }
    }
}