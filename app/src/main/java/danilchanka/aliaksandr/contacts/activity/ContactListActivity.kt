package danilchanka.aliaksandr.contacts.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import danilchanka.aliaksandr.contacts.R
import danilchanka.aliaksandr.contacts.fragment.ContactListFragment

class ContactListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.content, ContactListFragment.newInstance())
                    .commit()
        }
    }
}
