package masegi.sho.sharehub.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import masegi.sho.sharehub.App
import masegi.sho.sharehub.di.activitymodule.LoginActivityBuilder
import kotlin.reflect.jvm.internal.impl.javax.inject.Singleton

/**
 * Created by masegi on 2018/02/02.
 */

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewModelModule::class,
    LoginActivityBuilder::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance fun application(application: App) : Builder
        fun build(): AppComponent
    }

    override fun inject(app: App)
}