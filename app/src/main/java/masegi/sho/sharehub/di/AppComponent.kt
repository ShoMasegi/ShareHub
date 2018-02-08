package masegi.sho.sharehub.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import kotlin.reflect.jvm.internal.impl.javax.inject.Singleton

/**
 * Created by masegi on 2018/02/02.
 */
//
//@singleton
//@component(modules = [
//    androidsupportinjectionmodule::class,
//    appmodule::class
//])
//interface appcomponent : androidinjector<app> {
//
//    @component.builder
//    interface builder {
//
//        @bindsinstance fun application(application: app) : builder
//        fun build(): appcomponent
//    }
//
//    override fun inject(app: App)
//}