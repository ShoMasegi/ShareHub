package masegi.sho.sharehub.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import masegi.sho.sharehub.data.api.service.LoginService
import masegi.sho.sharehub.data.model.AccessToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by masegi on 2018/02/01.
 */

class GithubLoginUtils {

    companion object {

        val clientId = "79aedc3f8bf6e4d8df60"
        val clientSecret = "8ab86e0b63a7a4251b9a3485e18a3b7e4e78eeed"
        val redirectUrl = "sharehub://login"
        val scope = "user,repo"

        @JvmStatic val authorizationUrl =
                Uri.Builder().scheme("https")
                .authority("github.com")
                .appendPath("login")
                .appendPath("oauth")
                .appendPath("authorize")
                .appendQueryParameter("client_id", this.clientId)
                .appendQueryParameter("redirect_uri", this.redirectUrl)
                .appendQueryParameter("scope", this.scope)
//                .appendQueryParameter("state", BuildConfig.APPLICATION_ID)
                .build()


        @JvmStatic fun authorizationIntent(from: Context): Intent =
                Intent(Intent.ACTION_VIEW, this.authorizationUrl)


        @JvmStatic fun handleAuthIntent(intent: Intent?) {

            if (intent?.data != null) {

                val uri: Uri = intent.data
                if (uri.toString().startsWith(this.redirectUrl)) {

                    val code: String? = uri.getQueryParameter("code")
                    if (code != null) {

                        val accessToken: Call<AccessToken> = LoginService.getLoginRestClient().getAccessToken(
                                this.clientId,
                                this.clientSecret,
                                code
                        )

                        accessToken.enqueue(object : Callback<AccessToken> {

                            override fun onResponse(call: Call<AccessToken>?, response: Response<AccessToken>?) {

                                Log.e("Access_Token: ", "get!!")
                            }

                            override fun onFailure(call: Call<AccessToken>?, t: Throwable?) {

                                Log.e("Access_Token: ", "not found...")
                            }
                        })
                    }
                }
            }
        }
    }
}