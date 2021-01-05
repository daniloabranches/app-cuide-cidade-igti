package com.cuidedacidade.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object CameraUtils {
    fun hasCamera(context: Context) =
        context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)

    fun dispatchTakePicture(fragment: Fragment, requestCode: Int, onError: () -> Unit): String? {
        val context = fragment.requireContext()
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(context.packageManager) == null) {
            onError()
            return null
        }

        val photoFile = try {
            createImageFile(context)
        } catch (ex: IOException) {
            null
        }

        if (photoFile == null) {
            onError()
            return null
        }

        val photoURI = FileProvider.getUriForFile(
            fragment.requireContext(),
            "com.cuidedacidade.fileprovider",
            photoFile
        )

        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        fragment.startActivityForResult(intent, requestCode)

        return photoFile.absolutePath
    }

    private fun createImageFile(context: Context): File {
        val fileName = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            fileName,
            ".jpg",
            storageDir
        )
    }
}