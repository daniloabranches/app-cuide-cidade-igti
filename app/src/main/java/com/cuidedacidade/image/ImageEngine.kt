package com.cuidedacidade.image

import android.content.res.Resources
import com.cuidedacidade.extensions.getDensityQualifier
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object ImageEngine {
    private fun getStorageReference(resources: Resources, imagePath: String): StorageReference {
        val storage = FirebaseStorage.getInstance().reference
        val densityQualifier = resources.displayMetrics.getDensityQualifier()
        return storage.child("${imagePath}/${densityQualifier}")
    }

    fun getCategoriesStorageReference(resources: Resources) =
        getStorageReference(resources, "categories")
}