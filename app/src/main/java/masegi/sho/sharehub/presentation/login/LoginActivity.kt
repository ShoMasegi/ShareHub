package masegi.sho.sharehub.presentation.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Button

import masegi.sho.sharehub.R
import masegi.sho.sharehub.databinding.ActivityLoginBinding
import masegi.sho.sharehub.presentation.NavigationController
import masegi.sho.sharehub.presentation.common.BaseActivity
import masegi.sho.sharehub.presentation.common.DrawerMenu
import masegi.sho.sharehub.util.GithubLoginUtils
import javax.inject.Inject

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity() {

    @Inject lateinit var navigationController: NavigationController
    @Inject lateinit var drawerMenu: DrawerMenu

    private val binding by lazy {

        DataBindingUtil.setContentView<ActivityLoginBinding>(
                this,
                R.layout.activity_login
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {

            it.setDisplayHomeAsUpEnabled(false)
        }
        if (savedInstanceState == null) {

            navigationController.navigateToLogin()
        }
        drawerMenu.setup(binding.drawerLayout, binding.drawer, binding.toolbar)
    }

    override fun onBackPressed() {

        if (drawerMenu.closeDrawerIfNeeded()) {

            super.onBackPressed()
        }
    }
}
