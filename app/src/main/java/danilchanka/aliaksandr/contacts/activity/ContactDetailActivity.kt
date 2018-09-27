package danilchanka.aliaksandr.contacts.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import danilchanka.aliaksandr.contacts.R
import danilchanka.aliaksandr.contacts.activity.base.BaseFragmentActivity
import danilchanka.aliaksandr.contacts.databinding.ActivityContactDetailBinding
import danilchanka.aliaksandr.contacts.fragment.ContactDetailFragment
import danilchanka.aliaksandr.contacts.model.Contact
import kotlinx.android.synthetic.main.activity_contact_detail.*

class ContactDetailActivity : BaseFragmentActivity() {

    companion object {
        const val ARG_CONTACT = "ARG_CONTACT"
    }

    private var mContact: Contact? = null

    override fun getFragmentContainerId(): Int = R.id.content

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityContactDetailBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_contact_detail)
        mContact = intent.getParcelableExtra(ARG_CONTACT)
        setUpAppBar()
        binding.parallaxLayout!!.contact = mContact

        if (savedInstanceState == null) {
            addFragment(ContactDetailFragment.newInstance(mContact))
        }
    }

    private fun setUpAppBar() {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        title = mContact!!.name
    }
}
