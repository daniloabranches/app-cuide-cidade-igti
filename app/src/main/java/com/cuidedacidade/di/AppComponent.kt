package com.cuidedacidade.di

import android.content.Context
import com.cuidedacidade.features.newrequest.di.ChooseCategoryComponent
import com.cuidedacidade.features.newrequest.di.NewRequestComponent
import com.cuidedacidade.features.requests.di.RequestsComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModuleBinds::class,
        ViewModelBuilderModule::class,
        SubcomponentsModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun requestsComponent(): RequestsComponent.Factory
    fun requestComponent(): NewRequestComponent.Factory
    fun categoriesComponent(): ChooseCategoryComponent.Factory
}

@Module(
    subcomponents = [
        RequestsComponent::class,
        ChooseCategoryComponent::class,
        NewRequestComponent::class
    ]
)
object SubcomponentsModule