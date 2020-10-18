package com.cuidedacidade.domain.usecase

import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.exception.ValidationException
import com.cuidedacidade.domain.repository.RequestRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SaveRequestUseCase @Inject constructor(
    private val requestRepository: RequestRepository
) {
    operator fun invoke(userId: String, request: Request): Observable<Unit> {
        return Observable.create<Unit> {
            if (request.categoryName.isBlank()) {
                throw ValidationException("A categoria não foi informada")
            }
            if (request.description.isBlank()) {
                throw ValidationException("Informe a descrição para a solicitação")
            }
            if (request.image.isBlank()) {
                throw ValidationException("A imagem da categoria não foi informada")
            }
            it.onNext(Unit)
            it.onComplete()
        }.concatWith(requestRepository.saveRequest(userId, request))
    }
}