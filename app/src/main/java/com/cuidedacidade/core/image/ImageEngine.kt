package com.cuidedacidade.core.image

import android.content.res.Resources
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cuidedacidade.core.extensions.getDensityQualifier
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object ImageEngine {
    private fun getStorageReference(resources: Resources, imagePath: String): StorageReference {
        val storage = FirebaseStorage.getInstance().reference
        val densityQualifier = resources.displayMetrics.getDensityQualifier()
        return storage.child("${imagePath}/${densityQualifier}")
    }

    private fun getCategoriesStorageReference(resources: Resources) =
        getStorageReference(resources, "categories")

    fun getCategoryImage(imagePath: String, imageView: ImageView) {
        val reference =
            getCategoriesStorageReference(imageView.resources).child(imagePath)
        Glide.with(imageView).load(reference).into(imageView)
    }
}