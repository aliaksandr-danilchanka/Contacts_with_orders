package danilchanka.aliaksandr.contacts.repository

import android.app.Application
import android.os.AsyncTask
import danilchanka.aliaksandr.contacts.dao.ContactListDao
import danilchanka.aliaksandr.contacts.model.Contact
import danilchanka.aliaksandr.contacts.room.ContactListRoomDatabase
import io.reactivex.Flowable

class ContactListRepository(application: Application) {

    private var mContactListDao: ContactListDao? = null

    init {
        val db = ContactListRoomDatabase.getDatabase(application)
        mContactListDao = db!!.contactListDao()
    }

    fun deleteAndInsertAll(contactList: List<Contact>) {
        deleteAndInsertAsyncTask(mContactListDao!!, contactList).execute()
    }

    fun getContactListFromCache(): Flowable<List<Contact>>{
        return mContactListDao!!.getContactList()
    }

    companion object {

        private class deleteAndInsertAsyncTask(val dao: ContactListDao, val contactList: List<Contact>)
            : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                dao.deleteAll()
                for (contact in contactList) {
                    dao.insert(contact)
                }
                return null
            }
        }
    }
}