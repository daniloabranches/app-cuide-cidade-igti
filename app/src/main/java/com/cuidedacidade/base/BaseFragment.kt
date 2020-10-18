package com.cuidedacidade.base

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.cuidedacidade.R

abstract class BaseFragment : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupDI()
    }

    protected abstract fun setupDI()

    protected fun showAlert(message: String) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage(message)
            .setPositiveButton(
                getString(R.string.ok)
            ) { _, _ -> }
        builder.create().show()
    }

    protected fun hideKeyboard() {
        activity?.run {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            currentFocus?.run {
                imm.hideSoftInputFromWindow(windowToken, 0)
            }
        }
    }
}