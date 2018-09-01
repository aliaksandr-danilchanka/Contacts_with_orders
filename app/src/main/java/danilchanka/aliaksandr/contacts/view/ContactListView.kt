package danilchanka.aliaksandr.contacts.view

import danilchanka.aliaksandr.contacts.view.base.BaseView

interface ContactListView : BaseView {

    fun onConnectionError()

    fun onDatabaseError()

    fun startCreateNewContact()
}