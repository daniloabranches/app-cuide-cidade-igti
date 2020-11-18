package com.cuidedacidade.features.allrequests.di

import com.cuidedacidade.features.allrequests.AllRequestsFragment
import dagger.Subcomponent

@Subcomponent(modules = [AllRequestsModule::class])
interface AllRequestsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): AllRequestsComponent
    }

    fun inject(fragment: AllRequestsFragment)
}