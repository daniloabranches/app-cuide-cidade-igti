package com.cuidedacidade.features.allrequests.di

import androidx.lifecycle.ViewModel
import com.cuidedacidade.di.ViewModelKey
import com.cuidedacidade.features.allrequests.AllRequestsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AllRequestsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AllRequestsViewModel::class)
    abstract fun bindViewModel(viewModel: AllRequestsViewModel): ViewModel
}