package com.cuidedacidade.features.newrequest.di

import androidx.lifecycle.ViewModel
import com.cuidedacidade.di.ViewModelKey
import com.cuidedacidade.features.newrequest.NewRequestViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewRequestModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewRequestViewModel::class)
    abstract fun bindViewModel(newRequestViewModel: NewRequestViewModel): ViewModel
}