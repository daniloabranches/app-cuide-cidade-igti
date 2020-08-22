package com.cuidedacidade.data.repository

import com.cuidedacidade.data.entity.RequestEntity
import com.cuidedacidade.data.exception.FirebaseUnspecifiedException
import com.cuidedacidade.data.mapper.RequestEntityDataMapper
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.repository.RequestRepository
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Single

//TODO Testes

class RequestDataRepository(
    private val requestEntityDataMapper: RequestEntityDataMapper
) : RequestRepository {

    override fun getPendingRequests(userId: String): Single<MutableList<Request>> {
        return Single.fromCallable {
            val db = FirebaseFirestore.getInstance()
            val requests = db.collection("users").document(userId)
                .collection("requests")
                .whereEqualTo("status", Request.Status.PENDING.value).get()

            Tasks.await(requests)

            if (requests.isSuccessful) {
                requests.result?.let {
                    val dataRequests = it.map { document ->
                        val request = document.toObject(RequestEntity::class.java)
                        request.id = document.id
                        request
                    }
                    requestEntityDataMapper.transform(dataRequests)
                } ?: mutableListOf()
            } else {
                throw requests.exception ?: FirebaseUnspecifiedException()
            }
        }
    }
}