package com.cuidedacidade.ui.requestdetails.di

import androidx.lifecycle.ViewModel
import com.cuidedacidade.di.ViewModelKey
import com.cuidedacidade.ui.requestdetails.RequestDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RequestDetailsModule {
    @Binds
    @IntoMap
    @ViewModelKey(RequestDetailsViewModel::class)
    abstract fun bindViewModel(requestDetailsViewModel: RequestDetailsViewModel): ViewModel
}