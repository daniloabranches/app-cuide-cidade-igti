package com.cuidedacidade.feature.request

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.cuidedacidade.R
import kotlinx.android.synthetic.main.fragment_request_details.*

class RequestDetailsFragment : BaseRequestFragment() {
    private val args: RequestDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_category_request.editText?.setText(args.request.categoryName)
        txt_description_request.hint = getString(R.string.description)
        txt_description_request.editText?.apply {
            setText(args.request.description)
            isEnabled = false
        }
    }
}