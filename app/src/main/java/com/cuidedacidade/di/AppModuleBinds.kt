package com.cuidedacidade.di

import com.cuidedacidade.rx.AppSchedulerProvider
import com.cuidedacidade.rx.SchedulerProvider
import dagger.Binds
import dagger.Module

@Module
abstract class AppModuleBinds {
    @Binds
    abstract fun bindSchedulerProvider(schedulerProvider: AppSchedulerProvider): SchedulerProvider
}