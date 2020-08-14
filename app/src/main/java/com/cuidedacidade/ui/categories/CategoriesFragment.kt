package com.cuidedacidade.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cuidedacidade.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : Fragment() {

    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO Corrigir change do titulo da activity
        activity?.title = "Abrir solicitação"

        viewModel.getCategories()
            .observe(viewLifecycleOwner, Observer<List<Category>> { categories ->

                //TODO FloatingActionButton nao deve aparecer nesse fragment, mas deve ser reexibido depois
                activity?.fab?.hide()

                list_categories.apply {
                    //TODO Jogar para fora do apply
                    setHasFixedSize(true)
                    adapter =
                        CategoriesAdapter(categories)
                }
            })
    }
}