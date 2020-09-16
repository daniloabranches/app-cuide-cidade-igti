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
    @Binds
    @IntoMap
    @ViewModelKey(RequestsViewModel::class)
    abstract fun bindViewModel(viewModel: RequestsViewModel): ViewModel

    //TODO Correto?
    @Binds
    abstract fun bindModelDataMapper(requestModelDataMapper: RequestModelDataMapper): ModelDataMapper

    //TODO Correto?
    @Binds
    abstract fun bindEntityDataMapper(requestEntityDataMapper: RequestEntityDataMapper): EntityDataMapper

    //TODO Correto?
    @Binds
    abstract fun bindRequestRepository(requestDataRepository: RequestDataRepository): RequestRepository

    //TODO Correto?
    @Binds
    abstract fun bindUseCase(getPendingRequestsUseCase: GetPendingRequestsUseCase): UseCase
}