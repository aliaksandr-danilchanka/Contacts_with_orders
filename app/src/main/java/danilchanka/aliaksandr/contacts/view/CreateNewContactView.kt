package danilchanka.aliaksandr.contacts.view

import danilchanka.aliaksandr.contacts.view.base.BaseView

interface CreateNewContactView : BaseView {

    fun validateFields(): Boolean

    fun onContactCreated()

    fun showCreatingFailedError()

    fun hideSoftKeyboard()
}