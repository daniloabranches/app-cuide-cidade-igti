package com.cuidedacidade.features.startup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cuidedacidade.R
import com.cuidedacidade.core.BaseFragment
import com.cuidedacidade.core.CCidadeApplication
import com.cuidedacidade.core.auth.AuthManager
import com.cuidedacidade.core.router.Router
import kotlinx.android.synthetic.main.fragment_startup.*
import javax.inject.Inject

class StartupFragment : BaseFragment() {
    @Inject
    lateinit var appAuthManager: AuthManager

    @Inject
    lateinit var appRouter: Router

    override fun setupDI() {
        (requireActivity().application as CCidadeApplication).appComponent
            .startupComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_startup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (appAuthManager.isLoggedIn()) {
            handleUserAuthentication()
        } else {
            setupLogin()
        }
    }

    private fun handleUserAuthentication() =
        appRouter.startFirstDestinationAfterLogin(findNavController())

    private fun setupLogin() {
        btnLogin.setOnClickListener {
            appAuthManager.startSignIn(requireActivity())
        }
    }
}