package com.cuidedacidade.features.newrequest.di

import com.cuidedacidade.features.newrequest.ChooseCategoryFragment
import dagger.Subcomponent

@Subcomponent(modules = [ChooseCategoryModule::class])
interface ChooseCategoryComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ChooseCategoryComponent
    }

    fun inject(fragment: ChooseCategoryFragment)
}