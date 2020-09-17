package com.cuidedacidade.di

import androidx.lifecycle.ViewModel
import com.cuidedacidade.ui.requests.RequestsViewModel
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