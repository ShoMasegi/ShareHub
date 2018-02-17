package masegi.sho.sharehub.presentation.login


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dagger.android.support.DaggerFragment
import masegi.sho.sharehub.data.model.AccessToken
import masegi.sho.sharehub.databinding.FragmentSplashBinding

import masegi.sho.sharehub.presentation.NavigationController
import masegi.sho.sharehub.presentation.common.pref.Prefs
import masegi.sho.sharehub.util.ext.observeNonNull
import masegi.sho.sharehub.util.ext.setVisible
import javax.inject.Inject

class SplashScreenFragment : DaggerFragment() {

    private lateinit var binding: FragmentSplashBinding
    @Inject lateinit var navigationController: NavigationController
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val loginViewModel: LoginViewModel by lazy {

        ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentSplashBinding.inflate(inflater, container!!, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        setupLoginManage()
        if (!Prefs.accessToken.isNullOrEmpty()) {

            loginViewModel.getUser(AccessToken(accessToken = Prefs.accessToken))
        }
        else {

            Handler().postDelayed( {

                (activity as NavigationController.FragmentReplacable).replaceFragment(LoginFragment.newInstance())
            }, 1000)
        }
    }

    private fun setupLoginManage() {

        loginViewModel.isLoginSuccess.observeNonNull(this, {

            when(it) {
                true -> {

                    navigationController.navigateToMainActivity()
                    activity!!.finish()
                }
                false -> {

                    (activity as NavigationController.FragmentReplacable).replaceFragment(LoginFragment.newInstance())
                }
            }
        })
        loginViewModel.isLoading.observeNonNull(this, {

            binding.splashProgress.setVisible(it)
        })
    }

    companion object {

        fun newInstance(): SplashScreenFragment = SplashScreenFragment()
    }

}
