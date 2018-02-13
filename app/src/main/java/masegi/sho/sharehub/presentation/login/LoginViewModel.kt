package masegi.sho.sharehub.presentation.login

import android.arch.lifecycle.ViewModel
import android.util.Log
import masegi.sho.sharehub.data.api.GithubLoginApi
import masegi.sho.sharehub.data.model.AccessToken
import masegi.sho.sharehub.util.GithubLoginUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by masegi on 2018/02/03.
 */

class LoginViewModel @Inject constructor(
        private val api: GithubLoginApi
): ViewModel() {

    fun handleAuth(tokenCode: String) {

        api.getAccessToken(
                tokenCode,
                GithubLoginUtils.clientId,
                GithubLoginUtils.clientSecret,
                GithubLoginUtils.redirectUrl
        ).enqueue(object : Callback<AccessToken> {

            override fun onResponse(call: Call<AccessToken>?, response: Response<AccessToken>?) {

                Log.e("LoginVieModel", "GET Access Token!!")
            }

            override fun onFailure(call: Call<AccessToken>?, t: Throwable?) {

                Log.i("LoginVieModel", "Can not GET Access Token...")
            }
        })
    }
}