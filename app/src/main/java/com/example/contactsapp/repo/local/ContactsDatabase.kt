package com.example.contactsapp.repo.local

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.contactsapp.model.Contact
import com.example.contactsapp.repo.local.dao.ContactsDao

@Database(entities = [Contact::class], version = 1)
@TypeConverters(Converters::class)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun contactsDao() : ContactsDao

    companion object {
        private var INSTANCE : ContactsDatabase? = null
        private const val DB_NAME = "contacts.db"

        fun getDatabase(context: Context): ContactsDatabase {
            if(INSTANCE == null) {
                synchronized(ContactsDatabase::class.java) {
                    if(INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,ContactsDatabase::class.java, DB_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}