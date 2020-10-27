package com.cuidedacidade.core.auth

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

const val REQUEST_CODE_SIGN_IN = 1

class AppAuthManager @Inject constructor() : AuthManager {
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun isLoggedIn() = firebaseAuth.currentUser != null

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

    override fun getUserId() = firebaseAuth.currentUser?.uid ?: ""

    override fun signOut(activity: FragmentActivity, onSuccess: () -> Unit, onError: () -> Unit) {
        AuthUI.getInstance().signOut(activity)
            .addOnCompleteListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError()
            }
    }

    private fun getAvailableProviders() = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
}