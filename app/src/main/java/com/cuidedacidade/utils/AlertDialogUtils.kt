package com.cuidedacidade.utils

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.cuidedacidade.R

object AlertDialogUtils {
    fun showAlert(context: Context, message: String) =
        AlertDialog.Builder(context).setMessage(message)
            .setPositiveButton(
                context.getString(R.string.ok)
            ) { _, _ -> }.create().show()

    fun showAlert(
        context: Context,
        @StringRes messageId: Int,
        listener: DialogInterface.OnClickListener
    ) =
        AlertDialog.Builder(context).setMessage(messageId)
            .setPositiveButton(context.getString(R.string.ok), listener).create().show()
}