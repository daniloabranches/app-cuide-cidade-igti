package com.cuidedacidade.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cuidedacidade.R
import com.cuidedacidade.image.ImageEngine
import kotlinx.android.synthetic.main.item_category.view.*

class CategoriesAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val from = LayoutInflater.from(parent.context)
        val view = from.inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.run {
            val category = categories[position]
            txt_title_category.text = category.title

            val reference =
                ImageEngine.getCategoriesStorageReference(resources).child(category.image)
            Glide.with(this).load(reference).into(img_category)
        }
    }

    override fun getItemCount() = categories.size
}