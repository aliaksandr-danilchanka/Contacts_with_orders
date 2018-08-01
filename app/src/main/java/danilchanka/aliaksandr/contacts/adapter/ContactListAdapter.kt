package danilchanka.aliaksandr.contacts.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import danilchanka.aliaksandr.contacts.R
import danilchanka.aliaksandr.contacts.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactListAdapter(val contacts: List<Contact>?, private val context: Context?)
    : RecyclerView.Adapter<ContactListAdapter.ContactListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListHolder {
        return ContactListHolder(LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false))
    }

    override fun getItemCount(): Int {
        return contacts!!.size
    }

    override fun onBindViewHolder(holder: ContactListHolder, position: Int) {
        holder.mTxtName.text = contacts!![position].name
        holder.mTxtPhone.text = contacts[position].phone
        Picasso.with(holder.itemView.context)
                .load(contacts[position].pictureUrl)
                .placeholder(R.drawable.contact_icon)
                .into(holder.mImgUserIcon)
    }

    class ContactListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mImgUserIcon = view.img_user_icon!!
        val mTxtName = view.txt_name!!
        val mTxtPhone = view.txt_phone!!
    }
}