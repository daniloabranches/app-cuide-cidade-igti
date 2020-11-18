package com.cuidedacidade.features.pendingrequests.di

import androidx.lifecycle.ViewModel
import com.cuidedacidade.di.ViewModelKey
import com.cuidedacidade.features.pendingrequests.PendingRequestsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PendingRequestsModule {
    @Binds
    @IntoMap
    @ViewModelKey(PendingRequestsViewModel::class)
    abstract fun bindViewModel(viewModel: PendingRequestsViewModel): ViewModel
}