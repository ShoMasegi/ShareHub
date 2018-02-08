package masegi.sho.sharehub.data.api.service

import com.squareup.moshi.Moshi
import masegi.sho.sharehub.data.api.client.LoginClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by masegi on 2018/02/02.
 */

object LoginService {

    fun getLoginRestClient(): LoginClient {

        return Retrofit.Builder()
                .baseUrl("https://github.com/login/oauth/")
                .client(this.httpBuilder.build())
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(LoginClient::class.java)
    }

    private val httpBuilder: OkHttpClient.Builder get() {

        val httpClient = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(loggingInterceptor)
        return httpClient
    }
}