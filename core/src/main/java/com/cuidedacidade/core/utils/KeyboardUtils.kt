package com.cuidedacidade.core.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager

object KeyboardUtils {
    fun hideKeyboard(activity: Activity) {
        activity.run {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            currentFocus?.run {
                imm.hideSoftInputFromWindow(windowToken, 0)
            }
        }
    }
}