package danilchanka.aliaksandr.contacts.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import danilchanka.aliaksandr.contacts.R

class ContactListFragment : Fragment() {

    companion object {
        fun newInstance(): ContactListFragment = ContactListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }
}