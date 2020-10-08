package com.cuidedacidade.ui.requestdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.cuidedacidade.CCidadeApplication
import com.cuidedacidade.R
import com.cuidedacidade.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_request_details.*

class RequestDetailsFragment : BaseFragment() {
    private val requestDetailsViewModel: RequestDetailsViewModel by viewModels()

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

        txt_description_request.editText?.setOnFocusChangeListener { _, hasFocus ->
            txt_description_request.hint = getString(
                if (hasFocus) R.string.description else R.string.help_request_details
            )
        }
    }
}