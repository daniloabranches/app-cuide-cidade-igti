package com.cuidedacidade.log

import com.google.firebase.crashlytics.FirebaseCrashlytics

object Log {
    fun exception(exception: Throwable) {
        FirebaseCrashlytics.getInstance().recordException(exception)
    }
}