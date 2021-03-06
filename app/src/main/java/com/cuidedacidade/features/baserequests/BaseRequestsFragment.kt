package com.cuidedacidade.features.baserequests

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.cuidedacidade.R
import com.cuidedacidade.core.BaseFragment
import com.cuidedacidade.core.image.ImageEngine
import com.cuidedacidade.core.network.Resource
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.utils.SwipeRefreshUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_requests.*

abstract class BaseRequestsFragment : BaseFragment() {
    protected val observerRequests = Observer<Resource<List<Request>>> { requests ->
        when (requests) {
            is Resource.Loading -> setupUIRefreshing()
            is Resource.Success -> setupUI(requests.data)
            is Resource.Error -> setupUIWithError()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lst_requests.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        SwipeRefreshUtils.setDefaultColorSchemeResources(swp_requests)
    }

    private fun setupUI(requests: List<Request>?) {
        lst_requests.adapter = requests?.let { RequestsAdapter(it, ImageEngine) }
        swp_requests.isRefreshing = false
    }

    private fun setupUIRefreshing() {
        swp_requests.isRefreshing = true
    }

    private fun setupUIWithError() {
        swp_requests.isRefreshing = false
        Snackbar.make(
            requireActivity().mainCoordinatorLayout,
            R.string.something_unexpected_happened_try_again_later,
            Snackbar.LENGTH_LONG
        ).show()
    }
}