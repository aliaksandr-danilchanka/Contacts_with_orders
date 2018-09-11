package danilchanka.aliaksandr.contacts.viewmodel

import android.app.Application
import android.databinding.ObservableBoolean
import android.view.View
import danilchanka.aliaksandr.contacts.api.RestHelper
import danilchanka.aliaksandr.contacts.model.CreateContactRequest
import danilchanka.aliaksandr.contacts.view.CreateNewContactView
import danilchanka.aliaksandr.contacts.viewmodel.base.BaseLoadingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CreateNewContactViewModel(application: Application) : BaseLoadingViewModel<CreateNewContactView>(application) {

    var name: String = ""
    var phone: String = ""
    var isLoading = ObservableBoolean(false)

    var isError: Boolean = false

    override fun attachView(view: CreateNewContactView) {
        super.attachView(view)
        showViewErrors()
    }

    fun onSubmitClick(view: View) {
        if (isViewAttached()) {
            getView().hideSoftKeyboard()
        }
        if (getView().validateFields()) {
            sendNewContact()
        }
    }

    private fun sendNewContact() {
        isLoading.set(true)
        addSubscription(
                RestHelper.getRestInterface()
                        .createNewContact(CreateContactRequest(name, phone))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            if (isViewAttached()) {
                                getView().onContactCreated()
                            }
                        }, { error ->
                            error.printStackTrace()
                            isError = true
                            isLoading.set(false)
                            showViewErrors()
                        })
        )
    }

    fun changeName(s: CharSequence) {
        name = s.toString()
    }

    fun changePhone(s: CharSequence) {
        phone = s.toString()
    }

    private fun showViewErrors() {
        if (isViewAttached()) {
            if (isError) {
                getView().showCreatingFailedError()
                isError = false
            }
        }
    }
}