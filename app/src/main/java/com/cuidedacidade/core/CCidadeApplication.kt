package com.cuidedacidade.core

import androidx.multidex.MultiDexApplication
import com.cuidedacidade.di.AppComponent
import com.cuidedacidade.di.DaggerAppComponent
import com.cuidedacidade.log.Log
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class CCidadeApplication : MultiDexApplication() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler { e: Throwable ->
            Log.exception(e)
        }
    }
}