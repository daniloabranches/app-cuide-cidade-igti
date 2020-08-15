package com.cuidedacidade.ui.requests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.cuidedacidade.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_requests.*

class RequestsFragment : Fragment() {

    private val viewModel: RequestsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO Corrigir change do titulo da activity
        activity?.title = getString(R.string.app_name)

        list_requests.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        //TODO FloatingActionButton deve aparecer nesse fragment
        activity?.fab?.show()

        viewModel.getRequests()
            .observe(viewLifecycleOwner, Observer<List<Request>> { requests ->
                list_requests.adapter = RequestsAdapter(requests)
            })
    }
}