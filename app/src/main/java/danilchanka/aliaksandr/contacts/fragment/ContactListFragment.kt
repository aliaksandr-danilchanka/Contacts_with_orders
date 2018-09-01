package danilchanka.aliaksandr.contacts.fragment

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.Toast
import danilchanka.aliaksandr.contacts.R
import danilchanka.aliaksandr.contacts.activity.CreateNewContactActivity
import danilchanka.aliaksandr.contacts.databinding.FragmentContactListBinding
import danilchanka.aliaksandr.contacts.fragment.base.BaseFragment
import danilchanka.aliaksandr.contacts.view.ContactListView
import danilchanka.aliaksandr.contacts.viewmodel.ContactListViewModel
import kotlinx.android.synthetic.main.fragment_contact_list.*

class ContactListFragment : BaseFragment<FragmentContactListBinding, ContactListViewModel>(), ContactListView {

    private lateinit var mLinearLayoutManager: LinearLayoutManager

    companion object {
        fun newInstance(): ContactListFragment = ContactListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_list, container, false)
        mViewModel = ViewModelProviders.of(this).get(ContactListViewModel::class.java)
        mBinding.contactListViewModel = mViewModel
        mViewModel.attachView(this)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        swipe_to_refresh_layout.setOnRefreshListener {
            swipe_to_refresh_layout.isRefreshing = false
            mViewModel.onSwipeToRefresh()
        }
        mViewModel.getContactList()!!.observe(this, Observer { adapter ->
            recycler_view.adapter = adapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_contact_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            R.id.action_add -> {
                mViewModel.onActionAddClick()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDetach() {
        super.onDetach()
        mViewModel.detachView()
    }

    override fun onConnectionError() {
        Toast.makeText(context, R.string.internet_connection_error, Toast.LENGTH_SHORT).show()
    }

    override fun onDatabaseError() {
        Toast.makeText(context, R.string.no_data, Toast.LENGTH_SHORT).show()
    }

    override fun startCreateNewContact() {
        val intent = Intent(activity, CreateNewContactActivity::class.java)
        startActivityForResult(intent, CreateNewContactActivity.REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == CreateNewContactActivity.REQUEST_CODE) {
            val needToRefresh = data!!.getBooleanExtra(CreateNewContactActivity.ARG_NEED_TO_REFRESH, false)
            if (needToRefresh) {
                mViewModel.refreshContactList()
            }
        }
    }

    private fun initRecyclerView(){
        mLinearLayoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = mLinearLayoutManager
    }
}