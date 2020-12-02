package com.cuidedacidade.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import androidx.fragment.app.Fragment

object CameraUtils {
    fun hasCamera(context: Context) =
        context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)

    fun dispatchTakePicture(fragment: Fragment, requestCode: Int, onError: () -> Unit) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(fragment.requireContext().packageManager)?.run {
            fragment.startActivityForResult(intent, requestCode)
        } ?: run {
            onError()
        }
    }
}