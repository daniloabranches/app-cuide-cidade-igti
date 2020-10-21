package com.cuidedacidade.feature.requests.di

import com.cuidedacidade.feature.requests.BaseRequestsFragment
import dagger.Subcomponent

@Subcomponent(modules = [RequestsModule::class])
interface RequestsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): RequestsComponent
    }

    fun inject(fragment: BaseRequestsFragment)
}