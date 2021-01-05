package com.cuidedacidade.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

abstract class BaseRepository {
    val db by lazy {
        FirebaseFirestore.getInstance()
    }

    val storage by lazy {
        FirebaseStorage.getInstance().reference
    }
}