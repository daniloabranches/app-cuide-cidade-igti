package com.cuidedacidade.data.extensions

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import java.util.concurrent.TimeUnit

fun CollectionReference.addSync(data: Any, timeout: Long): Task<DocumentReference> {
    val task = add(data)
    Tasks.await(task, timeout, TimeUnit.MILLISECONDS)
    return task
}