package danilchanka.aliaksandr.contacts.api

import danilchanka.aliaksandr.contacts.model.ContactListResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface RestInterface {

    @GET("contactendpoint/v1/contact")
    fun getContactList(): Observable<ContactListResponse>

}