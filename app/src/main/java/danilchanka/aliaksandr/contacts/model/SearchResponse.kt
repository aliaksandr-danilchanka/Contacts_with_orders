package danilchanka.aliaksandr.contacts.model

import com.google.gson.annotations.SerializedName

data class Contact(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("phone") val phone: String,
        @SerializedName("pictureUrl") val pictureUrl: String
)

data class ContactListResponse(@SerializedName("items") val items: ArrayList<Contact>)