package com.cuidedacidade.features.newrequest.di

import com.cuidedacidade.features.newrequest.NewRequestFragment
import dagger.Subcomponent

@Subcomponent(modules = [NewRequestModule::class])
interface NewRequestComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): NewRequestComponent
    }

    fun inject(fragment: NewRequestFragment)
}