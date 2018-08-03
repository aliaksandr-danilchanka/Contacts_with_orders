package danilchanka.aliaksandr.contacts.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

@Entity(tableName = "contact_list_table")
data class Contact(

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        @SerializedName
        ("id") val id: String,

        @ColumnInfo(name = "name")
        @SerializedName("name") val name: String,

        @ColumnInfo(name = "phone")
        @SerializedName("phone") val phone: String,

        @ColumnInfo(name = "pictureUrl")
        @SerializedName("pictureUrl") val pictureUrl: String
)

data class ContactListResponse(@SerializedName("items") val items: ArrayList<Contact>)