package com.example.contactsapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "contacts"
)
@Parcelize
data class Contact @JvmOverloads constructor(

    val fName: String,
    val lName: String,
    val address: Address,
    val phone:List<String>, //used to be int tried to do long
    val email: List<String>
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}