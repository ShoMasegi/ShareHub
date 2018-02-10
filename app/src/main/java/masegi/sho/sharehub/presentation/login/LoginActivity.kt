package masegi.sho.sharehub.presentation.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Button

import masegi.sho.sharehub.R
import masegi.sho.sharehub.databinding.ActivityLoginBinding
import masegi.sho.sharehub.presentation.NavigationController
import masegi.sho.sharehub.presentation.common.BaseActivity
import masegi.sho.sharehub.util.GithubLoginUtils
import javax.inject.Inject

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity() {

    @Inject lateinit var navigationController: NavigationController

    private val binding by lazy {

        DataBindingUtil.setContentView<ActivityLoginBinding>(
                this,
                R.layout.activity_login
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button: Button = findViewById(R.id.login_browser_button)
        button.setOnClickListener {

            navigationController.navigationToExternalBrowser(GithubLoginUtils.authorizationUrl.toString())
        }
    }
}
