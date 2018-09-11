package danilchanka.aliaksandr.contacts.fragment

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import danilchanka.aliaksandr.contacts.R
import danilchanka.aliaksandr.contacts.activity.CreateNewContactActivity
import danilchanka.aliaksandr.contacts.databinding.FragmentCreateNewContactBinding
import danilchanka.aliaksandr.contacts.fragment.base.BaseFragment
import danilchanka.aliaksandr.contacts.util.Utils
import danilchanka.aliaksandr.contacts.view.CreateNewContactView
import danilchanka.aliaksandr.contacts.viewmodel.CreateNewContactViewModel
import kotlinx.android.synthetic.main.fragment_create_new_contact.*

class CreateNewContactFragment : BaseFragment<FragmentCreateNewContactBinding, CreateNewContactViewModel>(), CreateNewContactView {

    companion object {
        fun newInstance(): CreateNewContactFragment = CreateNewContactFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_new_contact, container, false)
        mViewModel = ViewModelProviders.of(this).get(CreateNewContactViewModel::class.java)
        mBinding.createNewContactViewModel = mViewModel
        mViewModel.attachView(this)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.action_add)
    }

    override fun onDetach() {
        super.onDetach()
        mViewModel.detachView()
    }

    override fun validateFields(): Boolean {
        return validateNonEmptyField(edit_phone, input_layout_phone) and validateNonEmptyField(edit_name, input_layout_name)
    }

    override fun onContactCreated() {
        Toast.makeText(context, R.string.create_new_contact_success, Toast.LENGTH_LONG).show()
        val intent = Intent()
        intent.putExtra(CreateNewContactActivity.ARG_NEED_TO_REFRESH, true)
        activity!!.setResult(Activity.RESULT_OK, intent)
        activity!!.finish()
    }

    override fun showCreatingFailedError() {
        Toast.makeText(context, R.string.error_create_new_user, Toast.LENGTH_SHORT).show()
    }

    override fun hideSoftKeyboard() {
        Utils.hideSoftKeyboard(context!!, create_new_contact_layout)
    }

    private fun validateNonEmptyField(editText: EditText, inputLayout: TextInputLayout): Boolean {
        val value = editText.text.toString().trim { it <= ' ' }

        when {
            value.isEmpty() -> {
                inputLayout.isErrorEnabled = true
                inputLayout.error = getString(R.string.error_empty_field)
                Utils.requestFocus(editText, activity!!)
                return false
            }
            value.length < 5 -> {
                inputLayout.isErrorEnabled = true
                inputLayout.error = getString(R.string.error_min_length_field)
                Utils.requestFocus(editText, activity!!)
                return false
            }
            else -> inputLayout.isErrorEnabled = false
        }

        return true
    }
}