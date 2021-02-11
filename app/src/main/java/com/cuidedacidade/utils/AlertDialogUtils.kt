package com.cuidedacidade.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.cuidedacidade.R

object AlertDialogUtils {
    fun showAlert(context: Context, message: String) =
        AlertDialog.Builder(context).setMessage(message)
            .setPositiveButton(
                context.getString(R.string.ok)
            ) { _, _ -> }.create().show()
}