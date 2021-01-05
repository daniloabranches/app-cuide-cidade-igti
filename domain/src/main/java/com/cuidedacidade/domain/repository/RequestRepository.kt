package com.cuidedacidade.domain.repository

import com.cuidedacidade.domain.entity.Request
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface RequestRepository {
    fun getPendingRequests(userId: String): Single<MutableList<Request>>
    fun getAllRequests(userId: String): Single<MutableList<Request>>
    fun saveRequest(userId: String, request: Request, photoPath: String?): Completable
}