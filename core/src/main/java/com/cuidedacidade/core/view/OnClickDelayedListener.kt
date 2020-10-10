package com.cuidedacidade.core.view

import android.os.Handler
import android.os.Looper
import android.view.View

abstract class OnClickDelayedListener : View.OnClickListener {
    var delayMillis: Long = 100

    override fun onClick(view: View) {
        Handler(Looper.getMainLooper()).postDelayed({
            onClickDelayed(view)
        }, delayMillis)
    }

    abstract fun onClickDelayed(view: View)
}