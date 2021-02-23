package com.example.contactsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.adapter.viewholders.ContactViewHolder
import com.example.contactsapp.adapter.viewholders.HeaderViewHolder
import com.example.contactsapp.databinding.ContactItemBinding
import com.example.contactsapp.databinding.RvHeaderBinding
import com.example.contactsapp.model.Contact
import com.example.contactsapp.model.Header
import com.example.contactsapp.util.Type
import com.example.contactsapp.util.ViewType

class MyAdapter(
    private val data: List<ViewType>,
    private val listener: ContactClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rvHeaderBinding = RvHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val contactItemBinding = ContactItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return when (Type.fromInt(viewType)) {
            Type.HEADER -> HeaderViewHolder(rvHeaderBinding)
            Type.CONTACT -> ContactViewHolder(contactItemBinding, listener)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = data[position]
        when(viewType.type) {
            Type.HEADER -> (holder as HeaderViewHolder).loadHeader(viewType as Header)
            Type.CONTACT -> (holder as ContactViewHolder).loadContact(viewType as Contact)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type.ordinal
    }
}