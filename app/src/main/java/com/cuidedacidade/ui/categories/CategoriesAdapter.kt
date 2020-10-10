package com.cuidedacidade.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cuidedacidade.R
import com.cuidedacidade.core.view.OnClickDelayedListener
import com.cuidedacidade.image.ImageEngine
import kotlinx.android.synthetic.main.item_category.view.*

class CategoriesAdapter(
    categories: List<Category>,
    private val imageEngine: ImageEngine
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private val categories: List<Category> by lazy {
        categories.sortedBy { it.title }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category, parent, false)
        return ViewHolder(view, imageEngine)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }

    override fun getItemCount() = categories.size

    private fun getItem(position: Int) = categories[position]

    class ViewHolder(
        view: View,
        private val imageEngine: ImageEngine
    ) : RecyclerView.ViewHolder(view) {
        lateinit var category: Category

        init {
            itemView.setOnClickListener(object : OnClickDelayedListener() {
                override fun onClickDelayed(view: View) {
                    navigateToNewRequest(category, view)
                }
            })
        }

        private fun navigateToNewRequest(
            category: Category,
            view: View
        ) {
            view.findNavController().navigate(R.id.RequestDetailsFragment)
        }

        fun bind(category: Category) {
            this.category = category
            itemView.apply {
                txt_title_category.text = category.title
                imageEngine.getCategoryImage(category.image, img_category)
            }
        }
    }
}