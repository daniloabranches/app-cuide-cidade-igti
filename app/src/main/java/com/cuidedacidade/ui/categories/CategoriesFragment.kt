package com.cuidedacidade.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cuidedacidade.R
import com.cuidedacidade.image.ImageEngine
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : Fragment() {

    //TODO Rever funcionalidade

    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_categories.apply {
            setHasFixedSize(true)
        }

        viewModel.getCategories()
            .observe(viewLifecycleOwner, Observer<List<Category>> { categories ->
                list_categories.adapter = CategoriesAdapter(categories, ImageEngine)
            })
    }
}