package com.cuidedacidade.di

import com.cuidedacidade.rx.AppSchedulerProvider
import com.cuidedacidade.rx.SchedulerProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {
    //TODO

    @Singleton
    @Binds
    abstract fun bindSchedulerProvider(schedulerProvider: AppSchedulerProvider): SchedulerProvider
}