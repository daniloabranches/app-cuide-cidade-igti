package com.cuidedacidade.feature.request.di

import com.cuidedacidade.feature.request.BaseRequestFragment
import dagger.Subcomponent

@Subcomponent(modules = [RequestModule::class])
interface RequestComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): RequestComponent
    }

    fun inject(fragment: BaseRequestFragment)
}