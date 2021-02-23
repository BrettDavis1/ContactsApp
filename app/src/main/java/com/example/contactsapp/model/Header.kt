package com.example.contactsapp.model

import com.example.contactsapp.util.Type
import com.example.contactsapp.util.ViewType

data class Header(val headerText: String) : ViewType {
    override val type = Type.HEADER
}