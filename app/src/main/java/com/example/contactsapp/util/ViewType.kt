package com.example.contactsapp.util

interface ViewType {
    val type: Type
}

enum class Type {
    HEADER, CONTACT;

    companion object {
        fun fromInt(value: Int) = Type.values().first {
            it.ordinal == value
        }
    }
}