package com.cuidedacidade.features.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.cuidedacidade.NavGraphDirections
import com.cuidedacidade.R
import com.cuidedacidade.core.CCidadeApplication
import com.cuidedacidade.core.auth.AuthManager
import com.cuidedacidade.core.auth.REQUEST_CODE_SIGN_IN
import com.cuidedacidade.core.router.Router
import com.cuidedacidade.utils.SnackbarUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var appAuthManager: AuthManager

    @Inject
    lateinit var appRouter: Router
    private var isLoggedIn = false

    private val authStateListener =
        AuthManager.AuthStateListener { stateIsLogged ->
            if (isLoggedIn && !stateIsLogged) {
                findNavController(nav_host_fragment).navigate(NavGraphDirections.loginAction())
                SnackbarUtils.show(
                    mainCoordinatorLayout,
                    R.string.you_signed_out_your_account
                )
            }
            isLoggedIn = stateIsLogged
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as CCidadeApplication).appComponent
            .mainComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupToolbar()
        setupNavController()
        setupFloatingButton()
    }

    private fun setupToolbar() {
        val navController = findNavController(nav_host_fragment)
        val appBarConfiguration = getAppBarConfiguration()
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setupNavController() {
        val navController = findNavController(nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.PendingRequestsFragment, R.id.AllRequestsFragment -> fab.show()
                else -> fab.hide()
            }
            when (destination.id) {
                R.id.StartupFragment -> supportActionBar?.hide()
                else -> supportActionBar?.show()
            }
        }
    }

    private fun setupFloatingButton() {
        fab.setOnClickListener {
            findNavController(nav_host_fragment).navigate(R.id.CategoriesFragment)
        }
    }

    private fun getAppBarConfiguration(): AppBarConfiguration {
        val navController = findNavController(nav_host_fragment)
        return AppBarConfiguration(
            setOf(
                navController.graph.startDestination,
                R.id.PendingRequestsFragment
            )
        )
    }

    override fun onStart() {
        super.onStart()
        appAuthManager.addAuthStateListener(authStateListener)
    }

    override fun onStop() {
        super.onStop()
        appAuthManager.removeAuthStateListener(authStateListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            appAuthManager.handleSignInResult(resultCode, data, this::handleUserAuthentication) {
                SnackbarUtils.show(
                    mainCoordinatorLayout,
                    R.string.something_unexpected_happened_try_again_later
                )
            }
        }
    }

    private fun handleUserAuthentication() =
        appRouter.startFirstDestinationAfterLogin(findNavController(nav_host_fragment))
}