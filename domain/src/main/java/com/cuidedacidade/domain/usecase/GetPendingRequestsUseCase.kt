package com.cuidedacidade.domain.usecase

import com.cuidedacidade.domain.repository.RequestRepository
import javax.inject.Inject

class GetPendingRequestsUseCase @Inject constructor(
    private val requestRepository: RequestRepository
): UseCase {
    operator fun invoke(userId: String) = requestRepository.getPendingRequests(userId)
}