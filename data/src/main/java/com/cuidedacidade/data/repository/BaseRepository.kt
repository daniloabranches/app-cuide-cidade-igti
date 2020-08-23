package com.cuidedacidade.data.repository

import com.google.firebase.firestore.FirebaseFirestore

abstract class BaseRepository {
    val db by lazy {
        FirebaseFirestore.getInstance()
    }
}