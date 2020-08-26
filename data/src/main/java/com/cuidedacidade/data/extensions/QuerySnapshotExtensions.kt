package com.cuidedacidade.data.extensions

import com.google.firebase.firestore.QuerySnapshot

fun <T> QuerySnapshot.toObjectsWithId(
    clazz: Class<T>
): List<T> {
    return ArrayList<T>().also {
        for (document in this) {
            it.add(document.toObjectWithId(clazz))
        }
    }
}