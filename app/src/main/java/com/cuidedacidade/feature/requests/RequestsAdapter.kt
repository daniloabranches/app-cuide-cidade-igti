package com.cuidedacidade.feature.requests

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuidedacidade.R
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.core.image.ImageEngine

class RequestsAdapter(requests: List<Request>, private val imageEngine: ImageEngine) :
    RecyclerView.Adapter<RequestViewHolder>() {

    private val requests: List<Request> by lazy {
        requests.sortedByDescending { it.date }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RequestViewHolder {
        val from = LayoutInflater.from(parent.context)
        val view = from.inflate(R.layout.item_request, parent, false)
        return RequestViewHolder(view, imageEngine)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val request = requests[position]
        holder.bind(request)
    }

    override fun getItemCount() = requests.size
}