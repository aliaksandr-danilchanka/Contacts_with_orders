package danilchanka.aliaksandr.contacts.activity

import android.os.Bundle
import danilchanka.aliaksandr.contacts.R
import danilchanka.aliaksandr.contacts.activity.base.BaseFragmentActivity
import danilchanka.aliaksandr.contacts.fragment.ContactListFragment
import kotlinx.android.synthetic.main.activity_contact_list.*

class ContactListActivity : BaseFragmentActivity() {

    override fun getFragmentContainerId(): Int = R.id.content

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)
        setUpAppBar()

        if (savedInstanceState == null) {
            addFragment(ContactListFragment.newInstance())
        }
    }

    private fun setUpAppBar() {
        setSupportActionBar(toolbar)
    }
}
