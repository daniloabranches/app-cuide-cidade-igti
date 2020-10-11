package com.cuidedacidade.ui.categories.di

import com.cuidedacidade.ui.categories.CategoriesFragment
import dagger.Subcomponent

@Subcomponent(modules = [CategoriesModule::class])
interface CategoriesComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CategoriesComponent
    }

    fun inject(fragment: CategoriesFragment)
}