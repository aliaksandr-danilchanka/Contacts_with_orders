package danilchanka.aliaksandr.contacts.fragment.base

import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import danilchanka.aliaksandr.contacts.activity.base.BaseActivity

abstract class BaseFragment<B: ViewDataBinding, VM: ViewModel> : Fragment() {

    lateinit var mBinding: B
    lateinit var mViewModel: VM

    fun setTitle(title: String?) {
        if (activity != null && (activity as BaseActivity).supportActionBar != null && title != null) {
            (activity as BaseActivity).supportActionBar!!.title = title
        }
    }

    fun setTitle(@StringRes titleResId: Int) {
        if (activity != null && (activity as BaseActivity).supportActionBar != null) {
            (activity as BaseActivity).supportActionBar!!.setTitle(titleResId)
        }
    }
}