package danilchanka.aliaksandr.contacts.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import danilchanka.aliaksandr.contacts.R
import danilchanka.aliaksandr.contacts.adapter.ContactListAdapter
import danilchanka.aliaksandr.contacts.viewModel.ContactListViewModel
import kotlinx.android.synthetic.main.fragment_contact_list.*

class ContactListFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    companion object {
        fun newInstance(): ContactListFragment = ContactListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = linearLayoutManager

        val model = ViewModelProviders.of(this).get(ContactListViewModel::class.java)
        model.getContactList()!!.observe(this, Observer { contacts ->
            recycler_view.adapter = ContactListAdapter(contacts, context)
        })
    }
}