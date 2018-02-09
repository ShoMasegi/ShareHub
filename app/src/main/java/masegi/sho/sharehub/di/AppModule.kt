package masegi.sho.sharehub.di

import android.app.Application
import android.content.Context
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import kotlin.reflect.jvm.internal.impl.javax.inject.Singleton

/**
 * Created by masegi on 2018/02/02.
 */

@Module
internal object AppModule {

    @Singleton @Provides @JvmStatic
    fun provideContext(application: Application): Context = application

}