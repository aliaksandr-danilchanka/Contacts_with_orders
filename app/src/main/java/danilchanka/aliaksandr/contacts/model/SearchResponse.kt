package danilchanka.aliaksandr.contacts.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
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
        @SerializedName("name") val name: String?,

        @ColumnInfo(name = "phone")
        @SerializedName("phone") val phone: String?,

        @ColumnInfo(name = "pictureUrl")
        @SerializedName("pictureUrl") val pictureUrl: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(pictureUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }
}

data class ContactListResponse(@SerializedName("items") val items: ArrayList<Contact>)