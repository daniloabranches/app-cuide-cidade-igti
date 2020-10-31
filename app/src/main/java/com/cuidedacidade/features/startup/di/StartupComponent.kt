package com.cuidedacidade.features.startup.di

import com.cuidedacidade.features.startup.StartupFragment
import dagger.Subcomponent

@Subcomponent
interface StartupComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): StartupComponent
    }

    fun inject(fragment: StartupFragment)
}