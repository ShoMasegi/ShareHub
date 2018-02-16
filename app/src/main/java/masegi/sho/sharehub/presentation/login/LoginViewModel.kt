package masegi.sho.sharehub.presentation.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import masegi.sho.sharehub.data.api.GithubLoginApi
import masegi.sho.sharehub.data.api.LoginProvider
import masegi.sho.sharehub.data.model.AccessToken
import masegi.sho.sharehub.data.model.Login
import masegi.sho.sharehub.presentation.common.pref.Prefs
import masegi.sho.sharehub.util.GithubLoginUtils
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by masegi on 2018/02/03.
 */

@Singleton
class LoginViewModel @Inject constructor(
        private val githubApi: GithubLoginApi
): ViewModel() {


    // MARK: - Internal

    internal var isLoginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    internal var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    internal fun handleAuth(tokenCode: String) {

        isLoading.value = true
        val accessToken = githubApi.getAccessToken(
                tokenCode,
                GithubLoginUtils.clientId,
                GithubLoginUtils.clientSecret,
                GithubLoginUtils.redirectUrl
        )
        accessToken
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = { t ->

                            isLoading.value = false
                            isLoginSuccess.value = false
                        },
                        onSuccess = { accessToken ->

                            Prefs.accessToken = accessToken.accessToken
                            login(accessToken)
                        }
                )
                .addTo(compositeDisposable)
    }


    // MARK: - Private

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private fun login(accessToken: AccessToken) {

        isLoading.value = true
        val api = LoginProvider.getLoginService(accessToken.accessToken, null)
        val login = api.loginAccessToken()
        login.observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = { t ->

                            Log.e("LoginViewModel", t.message)
                            isLoading.value = false
                            isLoginSuccess.value = false
                        },
                        onSuccess = {

                            isLoading.value = false
                            isLoginSuccess.value = true
                        }
                )
                .addTo(compositeDisposable)
    }


    // MARK: - ViewModel

    override fun onCleared() {

        super.onCleared()
        compositeDisposable.clear()
    }
}