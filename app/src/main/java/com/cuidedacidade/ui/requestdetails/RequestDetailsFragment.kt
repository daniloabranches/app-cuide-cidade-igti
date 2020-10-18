package com.cuidedacidade.ui.requestdetails

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cuidedacidade.CCidadeApplication
import com.cuidedacidade.R
import com.cuidedacidade.base.BaseFragment
import com.cuidedacidade.core.flow.Resource
import com.cuidedacidade.domain.exception.ValidationException
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_request_details.*
import javax.inject.Inject

class RequestDetailsFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<RequestDetailsViewModel> { viewModelFactory }
    private val args: RequestDetailsFragmentArgs by navArgs()

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

    override fun setupDI() {
        (requireActivity().application as CCidadeApplication).appComponent
            .requestDetailsComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_request_details, container, false)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.request_details, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSendRequest) {
            saveRequest()
        }
        return true
    }

    private fun saveRequest() {
        viewModel.saveRequest(
            args.category,
            txt_description_request.editText!!.text.toString()
        ).observe(viewLifecycleOwner, saveRequestObserver)
    }

    private fun onSuccessSaveRequest() {
        hideKeyboard()
        findNavController().navigate(R.id.finishNewRequestAction)
        Snackbar.make(
            requireActivity().mainCoordinatorLayout,
            R.string.request_sent,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onLoadingSaveRequest() {
        hideKeyboard()
        Snackbar.make(
            requireActivity().mainCoordinatorLayout,
            R.string.saving,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onErrorSaveRequest(throwable: Throwable?) {
        hideKeyboard()
        if (throwable is ValidationException) {
            showAlert(
                throwable.message
                    ?: getString(R.string.something_unexpected_happened_try_again_later)
            )
            return
        }

        findNavController().navigate(R.id.finishNewRequestAction)

        Snackbar.make(
            requireActivity().mainCoordinatorLayout,
            R.string.something_unexpected_happened_we_will_try_again,
            Snackbar.LENGTH_LONG
        ).show()
    }
}