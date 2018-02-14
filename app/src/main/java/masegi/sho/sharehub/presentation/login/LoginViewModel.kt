package masegi.sho.sharehub.presentation.login

import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import masegi.sho.sharehub.data.api.GithubLoginApi
import masegi.sho.sharehub.util.GithubLoginUtils
import javax.inject.Inject

/**
 * Created by masegi on 2018/02/03.
 */

class LoginViewModel @Inject constructor(
        private val api: GithubLoginApi
): ViewModel() {


    // MARK: - Internal

    internal fun handleAuth(tokenCode: String) {

        val accessToken = api.getAccessToken(
                tokenCode,
                GithubLoginUtils.clientId,
                GithubLoginUtils.clientSecret,
                GithubLoginUtils.redirectUrl
        )
        accessToken
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = { t ->

                            Log.e("LoginViewModel", "Error : ${t.message}")
                        },
                        onSuccess = { accessToken ->

                            Log.e("LoginViewModel", "GET AccessToken : ${accessToken.accessToken}")
                        }
                )
                .addTo(compositeDisposable)
    }


    // MARK: - Private

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    // MARK: - ViewModel

    override fun onCleared() {

        super.onCleared()
        compositeDisposable.clear()
    }
}