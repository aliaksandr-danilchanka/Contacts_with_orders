package danilchanka.aliaksandr.contacts.view

import android.view.View
import danilchanka.aliaksandr.contacts.model.Contact
import danilchanka.aliaksandr.contacts.view.base.BaseView

interface ContactListView : BaseView {

    fun onConnectionError()

    fun onDatabaseError()

    fun startCreateNewContact()

    fun onContactClick(contact: Contact, view: View)
}