package com.cuidedacidade.domain.usecase

import com.cuidedacidade.domain.repository.RequestRepository

class GetPendingRequestsUseCase(
    private val requestRepository: RequestRepository
) {
    operator fun invoke() = requestRepository.getPendingRequests()
}