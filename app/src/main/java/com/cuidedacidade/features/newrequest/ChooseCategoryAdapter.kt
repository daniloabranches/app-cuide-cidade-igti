package com.cuidedacidade.features.newrequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuidedacidade.R
import com.cuidedacidade.domain.entity.Category
import com.cuidedacidade.core.image.ImageEngine

class ChooseCategoryAdapter(
    categories: List<Category>,
    private val imageEngine: ImageEngine
) : RecyclerView.Adapter<ChooseCategoryViewHolder>() {

    private val categories: List<Category> by lazy {
        categories.sortedBy { it.title }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category, parent, false)
        return ChooseCategoryViewHolder(view, imageEngine)
    }

    override fun onBindViewHolder(holder: ChooseCategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }

    override fun getItemCount() = categories.size

    private fun getItem(position: Int) = categories[position]
}