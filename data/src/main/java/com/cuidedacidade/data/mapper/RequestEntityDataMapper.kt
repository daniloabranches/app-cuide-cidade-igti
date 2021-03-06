package com.cuidedacidade.data.mapper

import com.cuidedacidade.data.entity.RequestEntity
import com.cuidedacidade.domain.entity.Request
import javax.inject.Inject

class RequestEntityDataMapper @Inject constructor() {
    fun transform(dataRequests: List<RequestEntity>) =
        dataRequests.map {
            transform(it)
        }.toMutableList()

    private fun transform(dataRequest: RequestEntity) =
        Request(
            dataRequest.id,
            dataRequest.category_name,
            dataRequest.description,
            dataRequest.image,
            dataRequest.date,
            dataRequest.completion_date,
            Request.Status.valueOf(dataRequest.status)
        )
}