package com.cuidedacidade.core.image

import android.content.res.Resources
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cuidedacidade.core.extensions.getDensityQualifier
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object ImageEngine {
    private fun getAppStorageReference() = FirebaseStorage.getInstance().reference

    private fun getCategoriesStorageReference(resources: Resources): StorageReference {
        return getAppStorageReference().run {
            val densityQualifier = resources.displayMetrics.getDensityQualifier()
            child("categories/${densityQualifier}")
        }
    }

    fun loadCategoryImage(imagePath: String, imageView: ImageView) {
        getCategoriesStorageReference(imageView.resources).child(imagePath).let {
            Glide.with(imageView).load(it).into(imageView)
        }
    }

    fun getUsersStorageReference(userId: String, imageName: String) =
        getAppStorageReference().run {
            child("users/${userId}/${imageName}")
        }
}