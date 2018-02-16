package masegi.sho.sharehub.presentation.login

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.DaggerFragment

import masegi.sho.sharehub.R
import masegi.sho.sharehub.databinding.FragmentLoginBinding
import masegi.sho.sharehub.presentation.NavigationController
import masegi.sho.sharehub.util.GithubLoginUtils
import masegi.sho.sharehub.util.ext.observeNonNull
import masegi.sho.sharehub.util.ext.setVisible
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    private lateinit var binding: FragmentLoginBinding
    @Inject lateinit var navigationController: NavigationController
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val loginViewModel: LoginViewModel by lazy {

        ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentLoginBinding.inflate(inflater, container!!, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.loginBrowserButton.setOnClickListener {

            navigationController.navigationToExternalBrowser(GithubLoginUtils.authorizationUrl.toString())
        }
        setupLoginManage()
    }

    override fun onResume() {

        super.onResume()
    }

    private fun setupLoginManage() {

        loginViewModel.isLoginSuccess.observeNonNull(this, {

            when(it) {
                true -> {

                    navigationController.navigateToMainActivity()
                    activity!!.finish()
                }
                false -> {

                    Toast.makeText(context, R.string.login_fail_cant_get_token_data, Toast.LENGTH_LONG)
                            .show()
                }
            }
        })
        loginViewModel.isLoading.observeNonNull(this, {

            binding.loginButton.setVisible(!it)
            binding.loginBrowserButton.setVisible(!it)
            binding.loginProgress.setVisible(it)
        })
    }

    companion object {

        fun newInstance(): LoginFragment = LoginFragment()
    }

}
