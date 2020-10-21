package com.cuidedacidade.feature.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.cuidedacidade.core.CCidadeApplication
import com.cuidedacidade.R
import com.cuidedacidade.core.BaseFragment
import javax.inject.Inject

abstract class BaseRequestFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun setupDI() {
        (requireActivity().application as CCidadeApplication).appComponent
            .requestComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_request_details, container, false)
    }
}