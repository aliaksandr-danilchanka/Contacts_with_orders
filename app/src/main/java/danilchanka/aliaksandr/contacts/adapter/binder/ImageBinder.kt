package danilchanka.aliaksandr.contacts.adapter.binder

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import danilchanka.aliaksandr.contacts.R


 object ImageBinder {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?) {
        val context = imageView.context
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.contact_icon)
                .into(imageView)
    }
}