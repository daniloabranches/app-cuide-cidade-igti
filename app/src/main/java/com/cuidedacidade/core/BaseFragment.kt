package com.cuidedacidade.core

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cuidedacidade.R
import com.cuidedacidade.core.auth.AuthManager
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var appAuthManager: AuthManager
    protected var requireAuthentication = true

    private val authStateListener = object : AuthManager.AuthStateListener {
        override fun onAuthStateChanged(isLogged: Boolean) {
            if (!isLogged && requireAuthentication) {
                findNavController().navigate(R.id.redirectPendingRequestsAction)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CCidadeApplication).appComponent
            .baseFragmentComponent().create().inject(this)
        setupDI()
    }

    protected abstract fun setupDI()

    override fun onStart() {
        super.onStart()
        appAuthManager.addAuthStateListener(authStateListener)
    }

    override fun onStop() {
        super.onStop()
        appAuthManager.removeAuthStateListener(authStateListener)
    }
}