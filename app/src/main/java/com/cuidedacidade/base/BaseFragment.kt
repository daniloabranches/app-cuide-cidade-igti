package com.cuidedacidade.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupDI()
    }

    protected abstract fun setupDI()
}