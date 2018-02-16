package masegi.sho.sharehub.presentation

import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import masegi.sho.sharehub.data.api.LoginProvider
import masegi.sho.sharehub.presentation.common.pref.Prefs
import javax.inject.Inject

/**
 * Created by masegi on 2018/02/14.
 */

class MainViewModel @Inject constructor(): ViewModel() {


    internal fun login() {

        val accessToken = Prefs.accessToken
        val api = LoginProvider.getLoginService(accessToken, null)
        val login = api.getUser()
        login.observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = { t ->

                            Log.e("LoginViewModel", "Error : ${t.message}")
                            Prefs.accessToken = ""
                        },
                        onSuccess = { login ->

                            Log.e("LoginViewModel", "GET Login : ${login.login}")
                        }
                )
                .addTo(compositeDisposable)
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
}
