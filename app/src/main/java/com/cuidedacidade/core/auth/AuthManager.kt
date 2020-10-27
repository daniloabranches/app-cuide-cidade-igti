package com.cuidedacidade.core.auth

import androidx.fragment.app.FragmentActivity

interface AuthManager {
    fun isLoggedIn(): Boolean
    fun startSignIn(activity: FragmentActivity)
    fun handleSignInResult(resultCode: Int, onSuccess: () -> Unit, onError: () -> Unit)
    fun getUserId(): String
    fun signOut(activity: FragmentActivity, onSuccess: () -> Unit, onError: () -> Unit)
}