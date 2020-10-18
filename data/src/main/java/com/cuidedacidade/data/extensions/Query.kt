package com.cuidedacidade.data.extensions

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

fun Query.getSync(): Task<QuerySnapshot> {
    val task = get()
    Tasks.await(task)
    return task
}