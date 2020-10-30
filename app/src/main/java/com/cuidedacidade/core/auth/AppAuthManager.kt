package com.cuidedacidade.core.auth

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

const val REQUEST_CODE_SIGN_IN = 1

class AppAuthManager @Inject constructor() : AuthManager {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val authStateListeners = mutableListOf<AuthManager.AuthStateListener>()

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        authStateListeners.forEach {
            it.onAuthStateChanged(firebaseAuth.currentUser != null)
        }
    }

    init {
        firebaseAuth.removeAuthStateListener(authStateListener)
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun isLoggedIn() = firebaseAuth.currentUser != null

    override fun getUserId() = firebaseAuth.currentUser?.uid ?: ""

    override fun startSignIn(activity: FragmentActivity) =
        activity.startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(getAvailableProviders())
                .build(),
            REQUEST_CODE_SIGN_IN
        )

    override fun handleSignInResult(resultCode: Int, onSuccess: () -> Unit, onError: () -> Unit) {
        if (resultCode == Activity.RESULT_OK) {
            onSuccess()
        } else {
            onError()
        }
    }

    override fun signOut(activity: FragmentActivity, onSuccess: () -> Unit, onError: () -> Unit) {
        AuthUI.getInstance().signOut(activity)
            .addOnCompleteListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError()
            }
    }

    override fun addAuthStateListener(authStateListener: AuthManager.AuthStateListener) {
        authStateListeners.add(authStateListener)
        authStateListener.onAuthStateChanged(firebaseAuth.currentUser != null)
    }

    override fun removeAuthStateListener(authStateListener: AuthManager.AuthStateListener) {
        authStateListeners.remove(authStateListener)
    }

    private fun getAvailableProviders() = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
}