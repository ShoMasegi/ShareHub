package masegi.sho.sharehub

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import masegi.sho.sharehub.di.DaggerAppComponent

/**
 * Created by masegi on 2018/02/02.
 */

open class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder()
                .application(this)
                .build()
    }
}
