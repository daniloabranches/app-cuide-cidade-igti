package com.cuidedacidade.di

import com.cuidedacidade.R
import com.cuidedacidade.core.auth.AppAuthManager
import com.cuidedacidade.core.auth.AuthManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModuleProvides {
    @Singleton
    @Provides
    fun bindAuthManager(): AuthManager = AppAuthManager(R.mipmap.ic_launcher)
}