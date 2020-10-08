package com.cuidedacidade.ui.requestdetails.di

import com.cuidedacidade.ui.requestdetails.RequestDetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [RequestDetailsModule::class])
interface RequestDetailsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): RequestDetailsComponent
    }

    fun inject(detailsFragment: RequestDetailsFragment)
}