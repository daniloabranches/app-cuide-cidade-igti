package com.cuidedacidade.features.requests.di

import androidx.lifecycle.ViewModel
import com.cuidedacidade.di.ViewModelKey
import com.cuidedacidade.features.requests.RequestsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RequestsModule {
    @Binds
    @IntoMap
    @ViewModelKey(RequestsViewModel::class)
    abstract fun bindViewModel(viewModel: RequestsViewModel): ViewModel
}