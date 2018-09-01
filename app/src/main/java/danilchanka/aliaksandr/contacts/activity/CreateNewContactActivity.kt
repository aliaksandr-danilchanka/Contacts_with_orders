package danilchanka.aliaksandr.contacts.activity

import android.os.Bundle
import danilchanka.aliaksandr.contacts.R
import danilchanka.aliaksandr.contacts.activity.base.BaseFragmentActivity
import danilchanka.aliaksandr.contacts.fragment.CreateNewContactFragment
import kotlinx.android.synthetic.main.activity_create_new_contact.*

class CreateNewContactActivity : BaseFragmentActivity() {

    companion object {
        const val REQUEST_CODE = 0
        const val ARG_NEED_TO_REFRESH = "ARG_NEED_TO_REFRESH"
    }

    override fun getFragmentContainerId(): Int = R.id.content

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_contact)
        setUpAppBar()

        if (savedInstanceState == null) {
            addFragment(CreateNewContactFragment.newInstance())
        }
    }

    private fun setUpAppBar() {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }
}
