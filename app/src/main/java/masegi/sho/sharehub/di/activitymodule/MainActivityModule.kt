package masegi.sho.sharehub.di.activitymodule

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import masegi.sho.sharehub.di.ViewModelKey
import masegi.sho.sharehub.presentation.MainActivity
import masegi.sho.sharehub.presentation.MainViewModel

/**
 * Created by masegi on 2018/02/14.
 */

@Module
interface MainActivityModule {

    @Binds
    fun providesAppCompatActivity(activity: MainActivity): AppCompatActivity

    @Binds @IntoMap @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}
