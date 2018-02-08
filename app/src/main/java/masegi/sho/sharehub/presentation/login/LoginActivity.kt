package masegi.sho.sharehub.presentation.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import masegi.sho.sharehub.R
import masegi.sho.sharehub.util.GithubLoginUtils

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button: Button = findViewById(R.id.login_browser_button)
        button.setOnClickListener {

            this.startActivity(GithubLoginUtils.authorizationIntent(from = this))
        }
    }

}
