package danilchanka.aliaksandr.contacts.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import danilchanka.aliaksandr.contacts.api.RestHelper
import danilchanka.aliaksandr.contacts.model.Contact
import danilchanka.aliaksandr.contacts.model.ContactListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContactListViewModel : ViewModel() {

    private var mContactList: MutableLiveData<List<Contact>>? = null

    fun getContactList(): LiveData<List<Contact>>? {
        if (mContactList == null) {
            mContactList = MutableLiveData()
            loadData()
        }
        return mContactList
    }

    private fun loadData() {
        val apiService = RestHelper.create()
        apiService.getContactList()
                .map(ContactListResponse::items)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    mContactList!!.value = result
                }, { error ->
                    error.printStackTrace()
                })
    }
}