package com.cuidedacidade.features.main.di

import com.cuidedacidade.features.main.MainActivity
import dagger.Subcomponent

@Subcomponent()
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}