package danilchanka.aliaksandr.contacts.api

import danilchanka.aliaksandr.contacts.util.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestHelper {

    companion object Factory {

        private var sRestInterface: RestInterface? = null

        fun getRestInterface(): RestInterface {
            return if (sRestInterface == null) {
                sRestInterface = createRestInterface()
                sRestInterface!!
            } else {
                sRestInterface!!
            }
        }

        private fun createRestInterface(): RestInterface {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Utils.BASE_URL)
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