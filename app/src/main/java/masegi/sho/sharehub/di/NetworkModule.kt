package masegi.sho.sharehub.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import masegi.sho.sharehub.data.api.helper.ApplicationJsonAdapterFactory
import masegi.sho.sharehub.data.api.GithubApi
import masegi.sho.sharehub.data.api.GithubLoginApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by masegi on 2018/02/11.
 */

@Module
open class NetworkModule {

    companion object {

        val instance = NetworkModule()
    }

    @Singleton @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @RetrofitLoginGithub @Singleton @Provides
    fun provideRetrofitForGithubLogin(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://github.com/login/oauth/")
                .addConverterFactory(
                        MoshiConverterFactory.create(
                                Moshi.Builder()
                                        .add(ApplicationJsonAdapterFactory.instance)
                                        .build())
                )
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
    }

    @RetrofitGithub @Singleton @Provides
    fun provideRetrofitForGithub(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.github.com/")
                .addConverterFactory(
                        MoshiConverterFactory.create(
                                Moshi.Builder()
                                        .add(ApplicationJsonAdapterFactory.instance)
                                        .build())
                )
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
    }

    @Singleton @Provides
    open fun provideGithubApi(@RetrofitGithub retrofit: Retrofit): GithubApi {

        return retrofit.create(GithubApi::class.java)
    }

    @Singleton @Provides
    open fun provideLoginGithubApi(@RetrofitLoginGithub retrofit: Retrofit): GithubLoginApi {

        return retrofit.create(GithubLoginApi::class.java)
    }
}
