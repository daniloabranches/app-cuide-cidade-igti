package com.cuidedacidade.data.extensions

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference

fun CollectionReference.addSync(data: Any): Task<DocumentReference> {
    val task = add(data)
    Tasks.await(task)
    return task
}