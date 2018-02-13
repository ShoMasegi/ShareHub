package masegi.sho.sharehub.presentation.login

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val loginViewModel: LoginViewModel by lazy {

        ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

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

    override fun onNewIntent(intent: Intent?) {

        super.onNewIntent(intent)
        onHandleAuthIntent(intent)
    }

    override fun onResume() {

        super.onResume()
        onHandleAuthIntent(intent)
    }

    override fun onBackPressed() {

        if (drawerMenu.closeDrawerIfNeeded()) {

            super.onBackPressed()
        }
    }

    private fun onHandleAuthIntent(intent: Intent?) {

        if (intent != null && intent.data != null) {

            val uri = intent.data
            if (uri.toString().startsWith(GithubLoginUtils.redirectUrl)) {

                val tokenCode = uri.getQueryParameter("code")
                if(!tokenCode.isNullOrEmpty()) {

                    loginViewModel.handleAuth(tokenCode)
                }
                else {

                }
            }
        }
    }
}
