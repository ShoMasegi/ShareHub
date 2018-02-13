package masegi.sho.sharehub.presentation

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import masegi.sho.sharehub.R
import masegi.sho.sharehub.util.GithubLoginUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.startActivity(GithubLoginUtils.authorizationIntent(from = this))
    }

    override fun onNewIntent(intent: Intent?) {

        super.onNewIntent(intent)
        GithubLoginUtils.handleAuthIntent(intent)
    }

    override fun onResume() {

        super.onResume()
        GithubLoginUtils.handleAuthIntent(intent)
    }
}
