package danilchanka.aliaksandr.contacts.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import danilchanka.aliaksandr.contacts.R

class CreateNewContactFragment : Fragment() {

    companion object {
        fun newInstance(): CreateNewContactFragment = CreateNewContactFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_new_contact, container, false);
    }
}