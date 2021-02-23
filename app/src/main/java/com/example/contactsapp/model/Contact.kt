package com.example.contactsapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.contactsapp.util.Type
import com.example.contactsapp.util.ViewType
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
) : Parcelable, ViewType {
    @Ignore override val type = Type.CONTACT
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}