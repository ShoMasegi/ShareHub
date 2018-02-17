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
import masegi.sho.sharehub.data.model.AuthModel
import masegi.sho.sharehub.presentation.common.pref.Prefs
import masegi.sho.sharehub.util.GithubLoginUtils
import masegi.sho.sharehub.util.ext.code
import okhttp3.Credentials
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by masegi on 2018/02/03.
 */

@Singleton
class LoginViewModel @Inject constructor(): ViewModel() {


    // MARK: - Internal

    internal var isLoginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    internal var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    internal var isTwoFactor: MutableLiveData<Boolean> = MutableLiveData()

    internal fun handleAuth(tokenCode: String) {

        isLoading.value = true
        val accessToken = LoginProvider.getOauthLoginService().getAccessToken(
                tokenCode,
                GithubLoginUtils.clientId,
                GithubLoginUtils.clientSecret,
                GithubLoginUtils.redirectUrl
        )
        accessToken
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {

                            isLoading.value = false
                            isLoginSuccess.value = false
                        },
                        onSuccess = { accessToken ->

                            accessToken.accessToken?.let {

                                Prefs.accessToken = it
                                getUser(accessToken)
                            }
                        }
                )
                .addTo(compositeDisposable)
    }

    internal fun login(username: String, password: String, twoFactorCode: String?) {

        isLoading.value = true
        val authModel = AuthModel(twoFactorCode)
        val authToken = Credentials.basic(username, password)
        val response = LoginProvider.getLoginService(authToken, twoFactorCode).login(authModel)
        response.observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = this::onError,
                        onSuccess = { accessToken ->

                            accessToken.token?.let {

                                Prefs.accessToken = it
                                getUser(accessToken)
                            }
                        }
                )
    }

    internal fun getUser(accessToken: AccessToken) {

        isLoading.value = true
        val token = accessToken.accessToken ?: accessToken.token
        if (token == null) {

            isLoading.value = false
            isLoginSuccess.value = false
            return
        }
        LoginProvider.getLoginService(token, null)
                .getUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = this::onError,
                        onSuccess = {

                            isLoading.value = false
                            isLoginSuccess.value = true
                        }
                )
                .addTo(compositeDisposable)
    }


    // MARK: - Private

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private fun onError(throwable: Throwable) {

        if (throwable.code == 401 && throwable is HttpException) {

            val response = throwable.response()
            if (response?.headers() != null) {

                val twoFactorToken = response.headers().get("X-GitHub-OTP")
                if (twoFactorToken != null) {

                    isTwoFactor.value = true
                    isLoading.value = false
                    return
                }
            }
        }
        Log.e("LoginViewModel", throwable.message)
        isLoading.value = false
        isLoginSuccess.value = false
    }


    // MARK: - ViewModel

    override fun onCleared() {

        super.onCleared()
        compositeDisposable.clear()
    }
}