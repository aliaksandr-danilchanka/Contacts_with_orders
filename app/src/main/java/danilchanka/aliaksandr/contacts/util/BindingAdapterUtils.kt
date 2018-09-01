package danilchanka.aliaksandr.contacts.util

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import danilchanka.aliaksandr.contacts.R

object BindingAdapterUtils {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?) {
        val context = imageView.context
        Picasso.with(context)
                .load(Utils.getContactPictureUrl(url))
                .placeholder(R.drawable.contact_icon)
                .into(imageView)
    }

    @BindingAdapter("hide")
    @JvmStatic
    fun setHide(view: View, hide: Boolean) {
        view.visibility = if (hide) View.GONE else View.VISIBLE
    }

    @BindingAdapter("show")
    @JvmStatic
    fun setShow(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}