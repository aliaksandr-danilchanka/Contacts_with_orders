package danilchanka.aliaksandr.contacts.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

class Utils {

    companion object {

        private const val CONTACT_PICTURE_URL_PREFIX = "https://inloop-contacts.appspot.com/"
        const val BASE_URL = "https://inloop-contacts.appspot.com/_ah/api/"

        fun getContactPictureUrl(pictureUrl: String?): String {
            return CONTACT_PICTURE_URL_PREFIX + pictureUrl
        }

        fun hideSoftKeyboard(context: Context, view: View) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun requestFocus(view: View, activity: Activity) {
            if (view.requestFocus()) {
                activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
            }
        }
    }
}