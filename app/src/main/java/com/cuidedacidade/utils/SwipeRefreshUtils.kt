package com.cuidedacidade.utils

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cuidedacidade.R

object SwipeRefreshUtils {
    fun setDefaultColorScheme(context: Context, swipeRefresh: SwipeRefreshLayout) {
        swipeRefresh.setColorSchemeColors(ContextCompat.getColor(context, R.color.colorAccent))
    }
}