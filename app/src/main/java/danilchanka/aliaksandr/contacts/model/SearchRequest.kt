package danilchanka.aliaksandr.contacts.model

import com.google.gson.annotations.SerializedName

data class CreateContactRequest(
        @SerializedName("name") val name: String?,
        @SerializedName("phone") val phone: String?
)