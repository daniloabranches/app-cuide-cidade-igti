package com.cuidedacidade.ui.requests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuidedacidade.R
import com.cuidedacidade.domain.entity.Request
import kotlinx.android.synthetic.main.item_request.view.*

class RequestsAdapter(private val requests: List<Request>) :
    RecyclerView.Adapter<RequestsAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val from = LayoutInflater.from(parent.context)
        val view = from.inflate(R.layout.item_request, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.run {
            val request = requests[position]
            txt_category_request.text = request.categoryName
            txt_description_request.text = request.description
            txt_date_request.text = "15 de ago"

            //TODO Aqui deve usar o Glide
            //img_request.setBackgroundResource(Integer.parseInt(request.image))
        }
    }

    override fun getItemCount() = requests.size
}