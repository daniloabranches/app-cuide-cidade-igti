package com.cuidedacidade.data.repository

import com.cuidedacidade.data.entity.RequestEntity
import com.cuidedacidade.data.exception.FirebaseUnspecifiedException
import com.cuidedacidade.data.extensions.addSync
import com.cuidedacidade.data.extensions.getSync
import com.cuidedacidade.data.extensions.toObjectsWithId
import com.cuidedacidade.data.mapper.RequestEntityDataMapper
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.repository.RequestRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RequestDataRepository @Inject constructor(
    private val requestEntityDataMapper: RequestEntityDataMapper
) : BaseRepository(), RequestRepository {

    override fun getPendingRequests(userId: String): Single<MutableList<Request>> {
        return Single.fromCallable {
            val requests = db.collection("users").document(userId)
                .collection("requests")
                .whereEqualTo("status", Request.Status.PENDING.value).getSync()

            if (requests.isSuccessful && requests.result != null) {
                val dataRequests = requests.result!!.toObjectsWithId(RequestEntity::class.java)
                requestEntityDataMapper.transform(dataRequests)
            } else {
                throw requests.exception ?: FirebaseUnspecifiedException()
            }
        }
    }

    override fun getAllRequests(userId: String): Single<MutableList<Request>> {
        return Single.fromCallable {
            val requests = db.collection("users").document(userId)
                .collection("requests").getSync()

            if (requests.isSuccessful && requests.result != null) {
                val dataRequests = requests.result!!.toObjectsWithId(RequestEntity::class.java)
                requestEntityDataMapper.transform(dataRequests)
            } else {
                throw requests.exception ?: FirebaseUnspecifiedException()
            }
        }
    }

    override fun saveRequest(userId: String, request: Request): Completable {
        return Completable.fromCallable {
            val data = hashMapOf(
                "category_name" to request.categoryName,
                "description" to request.description,
                "image" to request.image,
                "date" to request.date,
                "status" to request.status.value
            )

            val task = db.collection("users").document(userId)
                .collection("requests").addSync(data, 5000)

            if (!task.isSuccessful || task.result == null) {
                throw task.exception ?: FirebaseUnspecifiedException()
            }
        }
    }
}