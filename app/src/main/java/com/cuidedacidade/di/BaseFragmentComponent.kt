package com.cuidedacidade.di

import com.cuidedacidade.core.BaseFragment
import dagger.Subcomponent

@Subcomponent
interface BaseFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): BaseFragmentComponent
    }

    fun inject(fragment: BaseFragment)
}