package com.cuidedacidade.domain.usecase

import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.repository.RequestRepository
import javax.inject.Inject

class SaveRequestUseCase @Inject constructor(
    private val requestRepository: RequestRepository
) {
    operator fun invoke(userId: String, request: Request) =
        requestRepository.saveRequest(userId, request)
}