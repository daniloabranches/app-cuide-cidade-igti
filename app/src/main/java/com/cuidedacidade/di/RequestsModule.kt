package com.cuidedacidade.di

import androidx.lifecycle.ViewModel
import com.cuidedacidade.data.mapper.EntityDataMapper
import com.cuidedacidade.data.mapper.RequestEntityDataMapper
import com.cuidedacidade.data.repository.RequestDataRepository
import com.cuidedacidade.domain.repository.RequestRepository
import com.cuidedacidade.domain.usecase.GetPendingRequestsUseCase
import com.cuidedacidade.domain.usecase.UseCase
import com.cuidedacidade.mapper.ModelDataMapper
import com.cuidedacidade.mapper.RequestModelDataMapper
import com.cuidedacidade.ui.requests.RequestsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RequestsModule {
    //TODO

    @Binds
    @IntoMap
    @ViewModelKey(RequestsViewModel::class)
    abstract fun bindViewModel(viewModel: RequestsViewModel): ViewModel

    @Binds
    abstract fun bindRequestModelDataMapper(requestModelDataMapper: RequestModelDataMapper): ModelDataMapper

    @Binds
    abstract fun bindRequestEntityDataMapper(requestEntityDataMapper: RequestEntityDataMapper): EntityDataMapper

    @Binds
    abstract fun bindRequestDataRepository(requestDataRepository: RequestDataRepository): RequestRepository

    @Binds
    abstract fun bindGetPendingRequestsUseCase(getPendingRequestsUseCase: GetPendingRequestsUseCase): UseCase
}