package com.cuidedacidade.utils

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cuidedacidade.R

object SwipeRefreshUtils {
    fun setDefaultColorSchemeResources(swipeRefresh: SwipeRefreshLayout) {
        swipeRefresh.setColorSchemeResources(R.color.colorAccent)
    }
}