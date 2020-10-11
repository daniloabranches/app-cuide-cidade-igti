package com.cuidedacidade.di

import com.cuidedacidade.data.repository.CategoryDataRepository
import com.cuidedacidade.data.repository.RequestDataRepository
import com.cuidedacidade.domain.repository.CategoryRepository
import com.cuidedacidade.domain.repository.RequestRepository
import com.cuidedacidade.rx.AppSchedulerProvider
import com.cuidedacidade.rx.SchedulerProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {
    @Binds
    abstract fun bindSchedulerProvider(schedulerProvider: AppSchedulerProvider): SchedulerProvider

    @Singleton
    @Binds
    abstract fun bindRequestRepository(requestDataRepository: RequestDataRepository): RequestRepository

    @Singleton
    @Binds
    abstract fun bindCategoryRepository(categoryDataRepository: CategoryDataRepository): CategoryRepository
}