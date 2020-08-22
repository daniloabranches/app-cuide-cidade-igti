package com.cuidedacidade.data.firebase

import com.cuidedacidade.data.entity.BaseEntity
import com.google.firebase.firestore.QueryDocumentSnapshot

fun <T> QueryDocumentSnapshot.toObjectWithId(valueType: Class<T>): T {
    return toObject(valueType).also {
        if (it is BaseEntity) {
            it.id = id
        } else {
            throw RuntimeException("${valueType.name} must inherit BaseEntity")
        }
    }
}