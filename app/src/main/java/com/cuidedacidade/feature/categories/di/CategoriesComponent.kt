package com.cuidedacidade.feature.categories.di

import com.cuidedacidade.feature.categories.CategoriesFragment
import dagger.Subcomponent

@Subcomponent(modules = [CategoriesModule::class])
interface CategoriesComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CategoriesComponent
    }

    fun inject(fragment: CategoriesFragment)
}