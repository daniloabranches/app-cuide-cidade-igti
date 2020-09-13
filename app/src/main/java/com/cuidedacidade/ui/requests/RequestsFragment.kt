package com.cuidedacidade.ui.requests

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.cuidedacidade.CCidadeApplication
import com.cuidedacidade.R
import com.cuidedacidade.base.Resource
import com.cuidedacidade.model.RequestModel
import kotlinx.android.synthetic.main.fragment_requests.*
import javax.inject.Inject

class RequestsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<RequestsViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as CCidadeApplication).appComponent
            .requestsComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lst_requests.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        activity?.let {
            swp_requests.setColorSchemeColors(ContextCompat.getColor(it, R.color.colorAccent))
        }

        swp_requests.setOnRefreshListener(viewModel::refreshRequests)

        viewModel.getRequests()
            .observe(viewLifecycleOwner, Observer<Resource<List<RequestModel>>> { requests ->
                when (requests) {
                    is Resource.Loading -> setupUIRefreshing()
                    is Resource.Success -> setupUI(requests.data)
                    is Resource.Error -> setupUIWithError()
                }
            })
    }

    private fun setupUI(requests: List<RequestModel>?) {
        lst_requests.adapter = requests?.let { RequestsAdapter(it) }

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