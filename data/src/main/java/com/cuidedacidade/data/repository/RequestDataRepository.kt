package com.cuidedacidade.data.repository

import com.cuidedacidade.data.entity.RequestEntity
import com.cuidedacidade.data.exception.FirebaseUnspecifiedException
import com.cuidedacidade.data.extensions.toObjectsWithId
import com.cuidedacidade.data.mapper.RequestEntityDataMapper
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.repository.RequestRepository
import com.google.android.gms.tasks.Tasks
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RequestDataRepository @Inject constructor(
    private val requestEntityDataMapper: RequestEntityDataMapper
) : BaseRepository(), RequestRepository {

    override fun getPendingRequests(userId: String): Single<MutableList<Request>> {
        return Single.fromCallable {
            val requests = db.collection("users").document(userId)
                .collection("requests")
                .whereEqualTo("status", Request.Status.PENDING.value).get()

            Tasks.await(requests)

            if (requests.isSuccessful && requests.result != null) {
                val dataRequests = requests.result!!.toObjectsWithId(RequestEntity::class.java)
                requestEntityDataMapper.transform(dataRequests)
            } else {
                throw requests.exception ?: FirebaseUnspecifiedException()
            }
        }
    }
}