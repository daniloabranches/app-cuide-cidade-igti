package com.cuidedacidade.data.repository

import com.cuidedacidade.data.entity.RequestEntity
import com.cuidedacidade.data.exception.FirebaseUnspecifiedException
import com.cuidedacidade.data.extensions.addSync
import com.cuidedacidade.data.extensions.getSync
import com.cuidedacidade.data.extensions.putStreamSync
import com.cuidedacidade.data.extensions.toObjectsWithId
import com.cuidedacidade.data.mapper.RequestEntityDataMapper
import com.cuidedacidade.data.repository.collections.RequestsCollection
import com.cuidedacidade.data.repository.collections.UsersCollection
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.repository.RequestRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject

class RequestDataRepository @Inject constructor(
    private val requestEntityDataMapper: RequestEntityDataMapper
) : BaseRepository(), RequestRepository {
    override fun getPendingRequests(userId: String): Single<MutableList<Request>> {
        return Single.fromCallable {
            getRequestsCollection(userId)
                .whereEqualTo(RequestsCollection.Fields.status, Request.Status.PENDING.value)
                .getSync().let { requests ->
                    if (requests.isSuccessful && requests.result != null) {
                        requests.result!!.toObjectsWithId(RequestEntity::class.java).let {
                            requestEntityDataMapper.transform(it)
                        }
                    } else {
                        throw requests.exception ?: FirebaseUnspecifiedException()
                    }
                }
        }
    }

    override fun getAllRequests(userId: String): Single<MutableList<Request>> {
        return Single.fromCallable {
            getRequestsCollection(userId).getSync().let { requests ->
                if (requests.isSuccessful && requests.result != null) {
                    requests.result!!.toObjectsWithId(RequestEntity::class.java).let {
                        requestEntityDataMapper.transform(it)
                    }
                } else {
                    throw requests.exception ?: FirebaseUnspecifiedException()
                }
            }
        }
    }

    override fun saveRequest(userId: String, request: Request, photoPath: String?): Completable {
        return Completable.fromCallable {
            val data = getHashMap(request)
            getRequestsCollection(userId).addSync(data).let { task ->
                if (!task.isSuccessful || task.result == null) {
                    throw task.exception ?: FirebaseUnspecifiedException()
                }
            }

            photoPath?.run {
                val photo = File(photoPath)
                val stream = FileInputStream(photo)
                getUsersStorageReference(userId, photo.name).putStreamSync(stream).let { task ->
                    if (!task.isSuccessful) {
                        throw task.exception ?: FirebaseUnspecifiedException()
                    }
                }
            }
        }
    }

    private fun getHashMap(request: Request) =
        hashMapOf(
            "category_name" to request.categoryName,
            "description" to request.description,
            "image" to request.image,
            "date" to request.date,
            "status" to request.status.value
        )

    private fun getRequestsCollection(userId: String) =
        db.collection(UsersCollection.name).document(userId)
            .collection(RequestsCollection.name)

    private fun getUsersStorageReference(userId: String, imageName: String) =
        storage.child("${UsersCollection.name}/${userId}/${imageName}")
}