package com.cuidedacidade.di

import android.content.Context
import com.cuidedacidade.feature.categories.di.CategoriesComponent
import com.cuidedacidade.feature.request.di.RequestComponent
import com.cuidedacidade.feature.requests.di.RequestsComponent
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
    fun requestComponent(): RequestComponent.Factory
    fun categoriesComponent(): CategoriesComponent.Factory
}

@Module(
    subcomponents = [
        RequestsComponent::class,
        CategoriesComponent::class,
        RequestComponent::class
    ]
)
object SubcomponentsModule