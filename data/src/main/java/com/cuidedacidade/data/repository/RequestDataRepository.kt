package com.cuidedacidade.data.repository

import com.cuidedacidade.data.entity.Request
import com.cuidedacidade.domain.repository.RequestRepository
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Single

class RequestDataRepository : RequestRepository {
    override fun getPendingRequests(): Single<List<com.cuidedacidade.domain.entity.Request>> {
        return Single.fromCallable {
            //TODO Firebase
            val db = FirebaseFirestore.getInstance()

            val task = db.collection("requests").get()
            Tasks.await(task)

            task.result?.toObjects(Request::class.java)?.map {
                com.cuidedacidade.domain.entity.Request(
                    it.categoryName,
                    it.description,
                    it.image,
                    it.date
                )
            }
        }
    }
}