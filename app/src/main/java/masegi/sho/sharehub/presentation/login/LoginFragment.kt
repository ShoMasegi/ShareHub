package masegi.sho.sharehub.presentation.login

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

import masegi.sho.sharehub.R
import masegi.sho.sharehub.databinding.FragmentLoginBinding
import masegi.sho.sharehub.presentation.NavigationController
import masegi.sho.sharehub.util.GithubLoginUtils
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    private lateinit var binding: FragmentLoginBinding
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var navigationController: NavigationController
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
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {

        fun newInstance(): LoginFragment = LoginFragment()
    }
}
