package com.cuidedacidade.features.newrequest.di

import androidx.lifecycle.ViewModel
import com.cuidedacidade.di.ViewModelKey
import com.cuidedacidade.features.newrequest.ChooseCategoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChooseCategoryModule {
    @Binds
    @IntoMap
    @ViewModelKey(ChooseCategoryViewModel::class)
    abstract fun bindViewModel(viewModel: ChooseCategoryViewModel): ViewModel
}