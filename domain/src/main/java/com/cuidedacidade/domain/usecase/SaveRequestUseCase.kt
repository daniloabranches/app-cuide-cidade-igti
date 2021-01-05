package com.cuidedacidade.domain.usecase

import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.exception.ValidationException
import com.cuidedacidade.domain.repository.RequestRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SaveRequestUseCase @Inject constructor(
    private val requestRepository: RequestRepository
) {
    operator fun invoke(userId: String, request: Request, photoPath: String?): Observable<Unit> {
        return Observable.create<Unit> {
            if (request.categoryName.isBlank()) {
                throw ValidationException("Informe a categoria para a solicitação")
            }
            if (request.description.isBlank()) {
                throw ValidationException("Informe a descrição para a solicitação")
            }
            if (request.image.isBlank()) {
                throw ValidationException("Informe a imagem da categoria para a solicitação")
            }
            it.onNext(Unit)
            it.onComplete()
        }.concatWith(requestRepository.saveRequest(userId, request, photoPath))
    }
}