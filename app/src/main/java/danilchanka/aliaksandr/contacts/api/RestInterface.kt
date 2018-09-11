package danilchanka.aliaksandr.contacts.api

import danilchanka.aliaksandr.contacts.model.Contact
import danilchanka.aliaksandr.contacts.model.ContactListResponse
import danilchanka.aliaksandr.contacts.model.CreateContactRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestInterface {

    @GET("contactendpoint/v1/contact")
    fun getContactList(): Observable<ContactListResponse>

    @POST("contactendpoint/v1/contact")
    fun createNewContact(@Body request: CreateContactRequest): Observable<Contact>
}