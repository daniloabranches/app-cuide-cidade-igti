package com.cuidedacidade.feature.request

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cuidedacidade.R
import com.cuidedacidade.core.network.Resource
import com.cuidedacidade.core.utils.KeyboardUtils
import com.cuidedacidade.domain.exception.ValidationException
import com.cuidedacidade.utils.AlertDialogUtils
import com.cuidedacidade.utils.SnackbarUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_request_details.*

class NewRequestFragment : BaseRequestFragment() {
    private val viewModel by viewModels<RequestViewModel> { viewModelFactory }
    private val args: NewRequestFragmentArgs by navArgs()

    private val saveRequestObserver = Observer<Resource<Unit>> {
        when (it) {
            is Resource.Loading -> onLoadingSaveRequest()
            is Resource.Success -> onSuccessSaveRequest()
            is Resource.Error -> onErrorSaveRequest(it.throwable)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.new_request, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSendRequest) {
            saveRequest()
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_category_request.editText?.setText(args.category.title)
        txt_description_request.editText?.setOnFocusChangeListener { _, hasFocus ->
            txt_description_request.hint = getString(
                if (hasFocus) R.string.description else R.string.help_request_details
            )
        }
    }

    private fun saveRequest() {
        viewModel.saveRequest(
            args.category,
            txt_description_request.editText!!.text.toString()
        ).observe(viewLifecycleOwner, saveRequestObserver)
    }

    private fun onSuccessSaveRequest() {
        activity?.let {
            KeyboardUtils.hideKeyboard(it)
            findNavController().navigate(R.id.finishNewRequestAction)
            SnackbarUtils.show(it.mainCoordinatorLayout, R.string.request_sent)
        }
    }

    private fun onLoadingSaveRequest() {
        activity?.let {
            KeyboardUtils.hideKeyboard(it)
            SnackbarUtils.show(it.mainCoordinatorLayout, R.string.saving)
        }
    }

    private fun onErrorSaveRequest(throwable: Throwable?) {
        activity?.let {
            KeyboardUtils.hideKeyboard(it)
            when (throwable) {
                is ValidationException ->
                    AlertDialogUtils.showAlert(it, throwable.validationMessage)
                else -> {
                    findNavController().navigate(R.id.finishNewRequestAction)
                    SnackbarUtils.show(
                        it.mainCoordinatorLayout,
                        R.string.something_unexpected_happened_we_will_try_again
                    )
                }
            }
        }
    }
}