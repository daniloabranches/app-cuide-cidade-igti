package com.cuidedacidade.utils

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

object SnackbarUtils {
    fun show(view: View, @StringRes resId: Int, duration: Int = Snackbar.LENGTH_LONG) =
        Snackbar.make(view, resId, duration).show()
}