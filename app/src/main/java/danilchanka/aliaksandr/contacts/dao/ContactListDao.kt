package danilchanka.aliaksandr.contacts.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import danilchanka.aliaksandr.contacts.model.Contact

@Dao
interface ContactListDao {

    @Insert
    fun insert(contact: Contact)

    @Query("DELETE FROM contact_list_table")
    fun deleteAll()

    @Query("SELECT * from contact_list_table ORDER BY name ASC")
    fun getContactList(): LiveData<List<Contact>>
}