package danilchanka.aliaksandr.contacts.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room.databaseBuilder
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import danilchanka.aliaksandr.contacts.dao.ContactListDao
import danilchanka.aliaksandr.contacts.model.Contact

@Database(entities = [(Contact::class)], version = 1)
abstract class ContactListRoomDatabase : RoomDatabase() {

    companion object {

        private var INSTANCE: ContactListRoomDatabase? = null

        fun getDatabase(context: Context): ContactListRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(ContactListRoomDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = databaseBuilder(context.applicationContext,
                                ContactListRoomDatabase::class.java, "contact_list_database")
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }

    abstract fun contactListDao(): ContactListDao
}