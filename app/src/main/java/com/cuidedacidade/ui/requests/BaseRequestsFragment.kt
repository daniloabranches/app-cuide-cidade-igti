package com.cuidedacidade.ui.requests

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cuidedacidade.CCidadeApplication
import com.cuidedacidade.R
import com.cuidedacidade.base.BaseFragment
import com.cuidedacidade.core.extensions.setDefaults
import com.cuidedacidade.core.flow.Resource
import com.cuidedacidade.image.ImageEngine
import com.cuidedacidade.ui.requests.model.RequestModel
import kotlinx.android.synthetic.main.fragment_requests.*
import javax.inject.Inject

open class BaseRequestsFragment : BaseFragment() {
    //TODO Como isso é fornecido?
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected val viewModel by viewModels<RequestsViewModel> { viewModelFactory }

    protected val observerRequests = Observer<Resource<List<RequestModel>>> { requests ->
        when (requests) {
            is Resource.Loading -> setupUIRefreshing()
            is Resource.Success -> setupUI(requests.data)
            is Resource.Error -> setupUIWithError()
        }
    }

    override fun setupDI() {
        (requireActivity().application as CCidadeApplication).appComponent
            .requestsComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lst_requests.setDefaults()

        //TODO Aplicar global
        activity?.let {
            swp_requests.setColorSchemeColors(ContextCompat.getColor(it, R.color.colorAccent))
        }
    }

    private fun setupUI(requests: List<RequestModel>?) {
        lst_requests.adapter = requests?.let { RequestsAdapter(it, ImageEngine) }
        swp_requests.isRefreshing = false
    }

    private fun setupUIRefreshing() {
        swp_requests.isRefreshing = true
    }

    private fun setupUIWithError() {
        Toast.makeText(activity, R.string.generic_error_requests_message, Toast.LENGTH_SHORT).show()
        swp_requests.isRefreshing = false
    }
}