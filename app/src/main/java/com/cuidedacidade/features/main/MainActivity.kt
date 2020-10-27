package com.cuidedacidade.features.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.cuidedacidade.R
import com.cuidedacidade.core.CCidadeApplication
import com.cuidedacidade.core.auth.AuthManager
import com.cuidedacidade.core.auth.REQUEST_CODE_SIGN_IN
import com.cuidedacidade.utils.AlertDialogUtils
import com.cuidedacidade.utils.SnackbarUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var appAuthManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as CCidadeApplication).appComponent
            .mainComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupNavController()
        setupFloatingButton()
    }

    private fun setupFloatingButton() {
        fab.setOnClickListener {
            navigateToNewRequest()
        }
    }

    private fun navigateToNewRequest() {
        if (appAuthManager.isLoggedIn()) {
            findNavController(nav_host_fragment).navigate(R.id.CategoriesFragment)
        } else {
            AlertDialogUtils.showAlert(
                this, R.string.you_need_login_continue
            ) { _, _ -> appAuthManager.startSignIn(this) }
        }
    }

    private fun setupNavController() {
        val navController = findNavController(nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.PendingRequestsFragment, R.id.AllRequestsFragment -> fab.show()
                else -> fab.hide()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            appAuthManager.handleSignInResult(resultCode, this::navigateToNewRequest) {
                SnackbarUtils.show(
                    mainCoordinatorLayout,
                    R.string.something_unexpected_happened_try_again_later
                )
            }
        }
    }
}