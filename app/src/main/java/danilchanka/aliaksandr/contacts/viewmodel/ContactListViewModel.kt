package danilchanka.aliaksandr.contacts.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.view.View
import danilchanka.aliaksandr.contacts.adapter.ContactListAdapter
import danilchanka.aliaksandr.contacts.api.RestHelper
import danilchanka.aliaksandr.contacts.model.Contact
import danilchanka.aliaksandr.contacts.model.ContactListResponse
import danilchanka.aliaksandr.contacts.repository.ContactListRepository
import danilchanka.aliaksandr.contacts.view.ContactListView
import danilchanka.aliaksandr.contacts.viewmodel.base.BaseLoadingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContactListViewModel(application: Application) : BaseLoadingViewModel<ContactListView>(application) {

    private var mContactList: MutableLiveData<ContactListAdapter>? = null
    private var mContactListRepository: ContactListRepository? = null

    var isLoading = ObservableBoolean(false)
    var isError = ObservableBoolean(false)

    private var isDatabaseError: Boolean = false
    private var isConnectionError: Boolean = false

    init {
        mContactListRepository = ContactListRepository(application)
    }

    override fun attachView(view: ContactListView) {
        super.attachView(view)
        showViewErrors()
    }

    fun reloadContactList(view: View) {
        loadData()
    }

    fun refreshContactList() {
        loadDataFromApi()
    }

    fun onSwipeToRefresh() {
        loadDataFromApi()
    }

    fun onActionAddClick() {
        if (isViewAttached()) {
            getView().startCreateNewContact()
        }
    }

    fun getContactList(): LiveData<ContactListAdapter>? {
        if (mContactList == null) {
            mContactList = MutableLiveData()
            loadData()
        }
        return mContactList
    }

    private fun getContactListFromCache() {
        addSubscription(
                mContactListRepository!!.getContactListFromCache()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ result ->
                            if (result.isEmpty()) {
                                isDatabaseError = true
                                isLoading.set(true)
                                isError.set(true)
                                showViewErrors()
                            }
                            if (mContactList!!.value == null) mContactList!!.value = ContactListAdapter(this)
                            mContactList!!.value!!.setElements(result)
                        }, { error ->
                            error.printStackTrace()
                        })
        )
    }

    private fun deleteAndInsertContactListFromCache(contactList: List<Contact>) {
        mContactListRepository!!.deleteAndInsertAll(contactList)
    }

    private fun loadData() {
        isLoading.set(true)
        isError.set(false)
        loadDataFromApi()
    }

    private fun loadDataFromApi() {
        addSubscription(
                RestHelper.getRestInterface()
                        .getContactList()
                        .map(ContactListResponse::items)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ result ->
                            if (mContactList!!.value == null) mContactList!!.value = ContactListAdapter(this)
                            mContactList!!.value!!.setElements(result)
                            deleteAndInsertContactListFromCache(result)
                            isLoading.set(false)
                        }, { error ->
                            error.printStackTrace()
                            getContactListFromCache()
                            isLoading.set(false)
                            isConnectionError = true
                            showViewErrors()
                        })
        )
    }

    fun onClickContact(view: View, contact: Contact) {
        if (isViewAttached()) {
            getView().onContactClick(contact, view)
        }
    }

    private fun showViewErrors() {
        if (isViewAttached()) {
            if (isDatabaseError) {
                getView().onDatabaseError()
                isDatabaseError = false
            }
            if (isConnectionError) {
                getView().onConnectionError()
                isConnectionError = false
            }
        }
    }
}