package com.cuidedacidade.di

import com.cuidedacidade.ui.requests.RequestsFragment
import dagger.Subcomponent

//TODO Ver escopos

@Subcomponent(modules = [RequestsModule::class])
interface RequestsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): RequestsComponent
    }

    fun inject(fragment: RequestsFragment)
}