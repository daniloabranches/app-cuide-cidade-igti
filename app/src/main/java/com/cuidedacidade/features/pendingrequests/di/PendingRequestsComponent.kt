package com.cuidedacidade.features.pendingrequests.di

import com.cuidedacidade.features.pendingrequests.PendingRequestsFragment
import dagger.Subcomponent

@Subcomponent(modules = [PendingRequestsModule::class])
interface PendingRequestsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): PendingRequestsComponent
    }

    fun inject(fragment: PendingRequestsFragment)
}