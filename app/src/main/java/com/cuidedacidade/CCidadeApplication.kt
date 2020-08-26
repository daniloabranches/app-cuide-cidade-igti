package com.cuidedacidade

import androidx.multidex.MultiDexApplication
import com.cuidedacidade.log.Log
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class CCidadeApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler { e: Throwable ->
            Log.exception(e)
        }
    }
}