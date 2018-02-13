package masegi.sho.sharehub.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import masegi.sho.sharehub.R
import masegi.sho.sharehub.util.GithubLoginUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.startActivity(GithubLoginUtils.authorizationIntent(from = this))
    }
}
