package danilchanka.aliaksandr.contacts.activity.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction

abstract class BaseFragmentActivity : BaseActivity() {

    companion object {
        const val DEFAULT_STACK = "DEFAULT_STACK"
    }

    fun addFragment(fragment: Fragment) {
        addFragment(fragment, false)
    }

    fun addFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager
                .beginTransaction()
                .add(getFragmentContainerId(), fragment)
        if (addToBackStack) {
            transaction.addToBackStack(DEFAULT_STACK)
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commitAllowingStateLoss()
    }

    protected abstract fun getFragmentContainerId(): Int
}