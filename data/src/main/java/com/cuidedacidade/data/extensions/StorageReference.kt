package com.cuidedacidade.data.extensions

import com.google.android.gms.tasks.Tasks
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.InputStream

fun StorageReference.putStreamSync(stream: InputStream): UploadTask {
    val task = putStream(stream)
    Tasks.await(task)
    return task
}