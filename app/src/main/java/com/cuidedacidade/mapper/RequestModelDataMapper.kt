package com.cuidedacidade.mapper

import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.model.RequestModel
import javax.inject.Inject

class RequestModelDataMapper @Inject constructor() :
    ModelDataMapper<List<Request>, List<RequestModel>> {

    override fun apply(requests: List<Request>): List<RequestModel> =
        requests.map {
            transform(it)
        }.toMutableList()

    private fun transform(request: Request) =
        RequestModel(
            request.id,
            request.categoryName,
            request.description,
            request.image,
            request.date,
            RequestModel.Status.valueOf(request.status.value)
        )
}