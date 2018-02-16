package masegi.sho.sharehub.presentation

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import masegi.sho.sharehub.R
import masegi.sho.sharehub.presentation.common.BaseActivity
import masegi.sho.sharehub.presentation.common.DrawerMenu
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var navigationController: NavigationController
    @Inject lateinit var drawerMenu: DrawerMenu
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel: MainViewModel by lazy {

        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    companion object {

        fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java)

        fun start(context: Context) {

            createIntent(context).let {

                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(it)
            }
        }
    }
}
