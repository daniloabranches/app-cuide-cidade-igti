package com.cuidedacidade.domain.repository

import com.cuidedacidade.domain.entity.Request
import io.reactivex.rxjava3.core.Single

interface RequestRepository {
    fun getPendingRequests(): Single<List<Request>>
}