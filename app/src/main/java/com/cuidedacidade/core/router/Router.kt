package com.cuidedacidade.core.router

import androidx.navigation.NavController

interface Router {
    fun startFirstDestinationAfterLogin(navController: NavController)
}