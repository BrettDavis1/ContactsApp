package com.example.contactsapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address (
    val streetAddress: String,
    val city: String,
    val state: String,
    val zipcode: Int
) : Parcelable