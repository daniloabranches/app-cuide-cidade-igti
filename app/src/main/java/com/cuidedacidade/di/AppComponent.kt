package com.cuidedacidade.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModuleBinds::class,
        ViewModelBuilderModule::class,
        SubComponentsModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun requestsComponent(): RequestsComponent.Factory
}