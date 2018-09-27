package danilchanka.aliaksandr.contacts.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import danilchanka.aliaksandr.contacts.R
import danilchanka.aliaksandr.contacts.model.Contact

class ContactDetailFragment : Fragment() {

    companion object {
        private const val KEY_CONTACT = "KEY_CONTACT"

        fun newInstance(contact: Contact?): ContactDetailFragment {
            val f = ContactDetailFragment()
            val b = Bundle()
            b.putParcelable(KEY_CONTACT, contact)
            f.arguments = b
            return f
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_contact_detail, container, false)
    }
}