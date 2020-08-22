package com.cuidedacidade.data.firebase

import com.google.firebase.firestore.QuerySnapshot
import java.util.*

fun <T> QuerySnapshot.toObjectsWithId(
    clazz: Class<T>
): List<T> {
    val result: MutableList<T> = ArrayList()
    for (document in this) {
        result.add(document.toObjectWithId(clazz))
    }
    return result
}