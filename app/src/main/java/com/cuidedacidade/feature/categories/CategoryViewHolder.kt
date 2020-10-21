package com.cuidedacidade.feature.categories

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cuidedacidade.core.view.OnClickDelayedListener
import com.cuidedacidade.domain.entity.Category
import com.cuidedacidade.feature.request.CategoryBundle
import com.cuidedacidade.core.image.ImageEngine
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryViewHolder(
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
        val categoryBundle = CategoryBundle(
            category.title,
            category.image
        )
        val action = CategoriesFragmentDirections.newRequestDetailsAction(categoryBundle)
        view.findNavController().navigate(action)
    }

    fun bind(category: Category) {
        this.category = category
        itemView.apply {
            txt_title_category.text = category.title
            imageEngine.getCategoryImage(category.image, img_category)
        }
    }
}