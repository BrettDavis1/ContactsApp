package com.example.contactsapp.repo.local

import androidx.room.TypeConverter
import com.example.contactsapp.model.Address
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {
    // email list string, phone list int
    val emailType = Types.newParameterizedType(List::class.java, String::class.java)
    val phoneType = Types.newParameterizedType(List::class.java, String::class.java)
    @TypeConverter
    fun addressToString(address: Address): String {
        val adapter = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(Address::class.java)
        return adapter.toJson(address)
    }
    @TypeConverter
    fun stringToAddress(jsonString: String): Address? {
        val adapter = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(Address::class.java)
        return adapter.fromJson(jsonString)
    }
    @TypeConverter
    fun emailToString(email: List<String>) : String {
        val adapter = Moshi.Builder().build().adapter<List<String>>(emailType)
        return adapter.toJson(email)
    }
    @TypeConverter
    fun stringToEmail(jsonString: String) : List<String> {
        val adapter = Moshi.Builder().build().adapter<List<String>>(emailType)
        return adapter.fromJson(jsonString) ?: emptyList()
    }
//back when phone was int
//    @TypeConverter
//    fun phoneToString(phone: List<String>) : String {
//        val adapter = Moshi.Builder().build().adapter<List<String>>(phoneType)
//        return adapter.toJson(phone)
//    }
//    @TypeConverter
//    fun stringToPhone(jsonString: String) : List<String> {
//        val adapter = Moshi.Builder().build().adapter<List<String>>(phoneType)
//        return adapter.fromJson(jsonString) ?: emptyList()
//    }
}