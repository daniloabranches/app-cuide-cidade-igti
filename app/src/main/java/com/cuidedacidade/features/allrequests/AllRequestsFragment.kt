package com.cuidedacidade.features.allrequests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.cuidedacidade.R
import com.cuidedacidade.core.CCidadeApplication
import com.cuidedacidade.features.baserequests.BaseRequestsFragment
import kotlinx.android.synthetic.main.fragment_requests.*
import javax.inject.Inject

class AllRequestsFragment : BaseRequestsFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<AllRequestsViewModel> { viewModelFactory }

    override fun setupDI() {
        (requireActivity().application as CCidadeApplication).appComponent
            .allRequestsComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_title_pending_requests.visibility = View.GONE
        swp_requests.setOnRefreshListener(viewModel::refreshAllRequests)
        viewModel.getAllRequests().observe(viewLifecycleOwner, observerRequests)
    }
}