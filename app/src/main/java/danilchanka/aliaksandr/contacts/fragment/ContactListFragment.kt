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
import danilchanka.aliaksandr.contacts.viewmodel.ContactListViewModel
import kotlinx.android.synthetic.main.fragment_contact_list.*

class ContactListFragment : Fragment() {

    private lateinit var mLinearLayoutManager: LinearLayoutManager

    companion object {
        fun newInstance(): ContactListFragment = ContactListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLinearLayoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = mLinearLayoutManager

        val model = ViewModelProviders.of(this).get(ContactListViewModel::class.java)
        model.getContactList()!!.observe(this, Observer { contacts ->
            val adapter = ContactListAdapter()
            adapter.setElements(contacts!!)
            recycler_view.adapter = adapter
        })
    }
}