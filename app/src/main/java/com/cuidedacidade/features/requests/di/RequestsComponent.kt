package com.cuidedacidade.features.requests.di

import com.cuidedacidade.features.requests.BaseRequestsFragment
import dagger.Subcomponent

@Subcomponent(modules = [RequestsModule::class])
interface RequestsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): RequestsComponent
    }

    fun inject(fragment: BaseRequestsFragment)
}