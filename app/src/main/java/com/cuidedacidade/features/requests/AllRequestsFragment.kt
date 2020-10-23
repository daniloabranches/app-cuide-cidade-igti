package com.cuidedacidade.features.requests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cuidedacidade.R
import kotlinx.android.synthetic.main.fragment_requests.*

class AllRequestsFragment : BaseRequestsFragment() {
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