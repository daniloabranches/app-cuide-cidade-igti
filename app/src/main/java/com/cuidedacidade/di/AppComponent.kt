package com.cuidedacidade.di

import android.content.Context
import com.cuidedacidade.ui.categories.di.CategoriesComponent
import com.cuidedacidade.ui.requestdetails.di.RequestDetailsComponent
import com.cuidedacidade.ui.requests.di.RequestsComponent
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
    fun requestDetailsComponent(): RequestDetailsComponent.Factory
    fun categoriesComponent(): CategoriesComponent.Factory
}

@Module(
    subcomponents = [
        RequestsComponent::class,
        CategoriesComponent::class,
        RequestDetailsComponent::class
    ]
)
object SubcomponentsModule