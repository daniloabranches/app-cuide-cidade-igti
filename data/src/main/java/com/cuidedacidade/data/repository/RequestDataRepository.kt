package com.cuidedacidade.data.repository

import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.repository.RequestRepository
import io.reactivex.rxjava3.core.Single
import java.util.*

class RequestDataRepository : RequestRepository {
    override fun getPendingRequests(): Single<List<Request>> {
        return Single.fromCallable {
            listOf(
                Request("Coleta", "Teste teste teste teste", "ic_recycle", Date()),
                Request("Iluminação Pública", "Teste teste teste teste", "ic_brightness", Date())
            )
        }
    }
}