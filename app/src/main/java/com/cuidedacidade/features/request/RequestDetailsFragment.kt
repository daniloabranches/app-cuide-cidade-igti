package com.cuidedacidade.features.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.cuidedacidade.R
import com.cuidedacidade.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_request_details.*
import java.text.SimpleDateFormat
import java.util.*

class RequestDetailsFragment : BaseFragment() {
    private val args: RequestDetailsFragmentArgs by navArgs()

    override fun setupDI() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_request_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_request_category.editText?.setText(args.request.categoryName)
        txt_request_description.editText?.setText(args.request.description)
        setupDateTextView()
        setupCompletionDateTextView()
        setupStatusTextView()
    }

    private fun setupDateTextView() {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        txt_request_date.editText?.setText(simpleDateFormat.format(args.request.date))
    }

    private fun setupCompletionDateTextView() {
        args.request.completionDate?.let {
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            txt_request_completion_date.editText?.setText(simpleDateFormat.format(it))
        } ?: run {
            txt_request_completion_date.visibility = View.GONE
        }
    }

    private fun setupStatusTextView() {
        txt_request_status.apply {
            when (args.request.status) {
                RequestBundle.Status.PENDING -> {
                    text = getString(R.string.this_request_pending)
                    setTextColor(ContextCompat.getColor(context, R.color.colorRequestPending))
                }
                RequestBundle.Status.EXECUTED -> {
                    text = getString(R.string.this_request_completed)
                    setTextColor(ContextCompat.getColor(context, R.color.colorRequestCompleted))
                }
            }
        }
    }
}