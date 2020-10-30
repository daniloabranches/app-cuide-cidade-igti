package com.cuidedacidade.core.auth

import androidx.fragment.app.FragmentActivity

interface AuthManager {
    fun isLoggedIn(): Boolean
    fun getUserId(): String
    fun startSignIn(activity: FragmentActivity)
    fun handleSignInResult(resultCode: Int, onSuccess: () -> Unit, onError: () -> Unit)
    fun signOut(activity: FragmentActivity, onSuccess: () -> Unit, onError: () -> Unit)
    fun addAuthStateListener(authStateListener: AuthStateListener)
    fun removeAuthStateListener(authStateListener: AuthStateListener)

    interface AuthStateListener {
        fun onAuthStateChanged(isLogged: Boolean)
    }
}