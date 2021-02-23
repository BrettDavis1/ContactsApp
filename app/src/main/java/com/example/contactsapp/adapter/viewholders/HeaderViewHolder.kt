package com.example.contactsapp.adapter.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.databinding.RvHeaderBinding
import com.example.contactsapp.model.Header

class HeaderViewHolder(
    private val binding: RvHeaderBinding
): RecyclerView.ViewHolder(binding.root) {
    fun loadHeader(header: Header) = with(binding) {
        tvHeader.text = header.headerText
    }
}