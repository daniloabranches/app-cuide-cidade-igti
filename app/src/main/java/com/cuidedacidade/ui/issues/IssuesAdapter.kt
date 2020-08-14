package com.cuidedacidade.ui.issues

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuidedacidade.R
import kotlinx.android.synthetic.main.item_issue.view.*

class IssuesAdapter(private val issues: List<Issue>) :
    RecyclerView.Adapter<IssuesAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val from = LayoutInflater.from(parent.context)
        val view = from.inflate(R.layout.item_issue, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.run {
            val issue = issues[position]
            txt_category_issue.text = issue.title
            txt_description_issue.text = issue.description

            //TODO Aqui deve usar o Glide
            img_issue.setBackgroundResource(Integer.parseInt(issue.image))
        }
    }

    override fun getItemCount() = issues.size
}