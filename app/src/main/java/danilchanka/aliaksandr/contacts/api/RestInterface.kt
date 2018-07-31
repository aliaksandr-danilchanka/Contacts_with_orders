package danilchanka.aliaksandr.contacts.api

import danilchanka.aliaksandr.contacts.model.ContactListResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RestInterface {

    @GET("contactendpoint/v1/contact")
    fun getContactList(): Observable<ContactListResponse>

    companion object Factory {
        fun create(): RestInterface {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://inloop-contacts.appspot.com/_ah/api/")
                    .client(getOkHttpClient())
                    .build()
            return retrofit.create(RestInterface::class.java)
        }

        private fun getOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
        }
    }
}