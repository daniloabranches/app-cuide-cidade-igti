package com.cuidedacidade.core.router

import androidx.navigation.NavController
import com.cuidedacidade.features.startup.StartupFragmentDirections
import javax.inject.Inject

class AppRouter @Inject constructor() : Router {
    override fun startFirstDestinationAfterLogin(navController: NavController) =
        navController.navigate(StartupFragmentDirections.showPendingRequestsAction())
}