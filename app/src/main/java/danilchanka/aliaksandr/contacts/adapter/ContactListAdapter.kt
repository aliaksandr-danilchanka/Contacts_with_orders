package danilchanka.aliaksandr.contacts.adapter

import danilchanka.aliaksandr.contacts.BR
import danilchanka.aliaksandr.contacts.R
import danilchanka.aliaksandr.contacts.adapter.base.BaseAdapter
import danilchanka.aliaksandr.contacts.model.Contact
import danilchanka.aliaksandr.contacts.viewmodel.ContactListViewModel

class ContactListAdapter(viewModel: ContactListViewModel) : BaseAdapter<Contact, ContactListViewModel>(viewModel) {

    override val itemLayoutId: Int = R.layout.item_contact
    override val variableId: Int = BR.contact
    override val viewModelId: Int = BR.contactListViewModel

}