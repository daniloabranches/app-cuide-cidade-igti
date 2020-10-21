package com.cuidedacidade.feature.request.di

import androidx.lifecycle.ViewModel
import com.cuidedacidade.di.ViewModelKey
import com.cuidedacidade.feature.request.RequestViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RequestModule {
    @Binds
    @IntoMap
    @ViewModelKey(RequestViewModel::class)
    abstract fun bindViewModel(requestViewModel: RequestViewModel): ViewModel
}