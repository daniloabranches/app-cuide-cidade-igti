package com.cuidedacidade.core.auth

import android.content.Intent
import androidx.fragment.app.FragmentActivity

interface AuthManager {
    fun isLoggedIn(): Boolean
    fun getUserId(): String
    fun startSignIn(activity: FragmentActivity)
    fun handleSignInResult(
        resultCode: Int,
        data: Intent?,
        onSuccess: () -> Unit,
        onError: () -> Unit
    )

    fun signOut(activity: FragmentActivity, onSuccess: () -> Unit, onError: () -> Unit)
    fun addAuthStateListener(authStateListener: AuthStateListener)
    fun removeAuthStateListener(authStateListener: AuthStateListener)

    fun interface AuthStateListener {
        fun onAuthStateChanged(isLogged: Boolean)
    }
}