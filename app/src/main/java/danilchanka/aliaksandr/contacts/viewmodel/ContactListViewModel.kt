package danilchanka.aliaksandr.contacts.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import danilchanka.aliaksandr.contacts.api.RestHelper
import danilchanka.aliaksandr.contacts.model.Contact
import danilchanka.aliaksandr.contacts.model.ContactListResponse
import danilchanka.aliaksandr.contacts.repository.ContactListRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContactListViewModel(application: Application) : AndroidViewModel(application) {

    private var mContactList: MutableLiveData<List<Contact>>? = null
    private var mContactListRepository: ContactListRepository? = null

    init {
        mContactListRepository = ContactListRepository(application)
    }

    fun getContactList(): LiveData<List<Contact>>? {
        if (mContactList == null) {
            mContactList = MutableLiveData()
            loadData()
        }
        return mContactList
    }

    private fun getContactListFromCache() {
        mContactListRepository!!.getContactListFromCache()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    mContactList!!.value = result
                }, { error ->
                    error.printStackTrace()
                })
    }

    private fun deleteAndInsertContactListFromCache(contactList: List<Contact>) {
        mContactListRepository!!.deleteAndInsertAll(contactList)
    }

    private fun loadData() {
        val apiService = RestHelper.create()
        apiService.getContactList()
                .map(ContactListResponse::items)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    mContactList!!.value = result
                    deleteAndInsertContactListFromCache(result)
                }, { error ->
                    error.printStackTrace()
                    getContactListFromCache()
                })
    }
}