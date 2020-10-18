package com.cuidedacidade.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cuidedacidade.CCidadeApplication
import com.cuidedacidade.R
import com.cuidedacidade.base.BaseFragment
import com.cuidedacidade.core.flow.Resource
import com.cuidedacidade.image.ImageEngine
import com.cuidedacidade.ui.categories.model.CategoryModel
import com.cuidedacidade.utils.SwipeRefreshUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_categories.*
import javax.inject.Inject

class CategoriesFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CategoriesViewModel> { viewModelFactory }

    override fun setupDI() {
        (requireActivity().application as CCidadeApplication).appComponent
            .categoriesComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_categories.setHasFixedSize(true)
        SwipeRefreshUtils.setDefaultColorScheme(requireActivity(), swp_requests)
        viewModel.getCategories().observe(viewLifecycleOwner, observerCategories)
    }

    private val observerCategories = Observer<Resource<List<CategoryModel>>> { categories ->
        when (categories) {
            is Resource.Loading -> setupUIRefreshing()
            is Resource.Success -> setupUI(categories.data)
            is Resource.Error -> setupUIWithError()
        }
    }

    private fun setupUI(categories: List<CategoryModel>?) {
        list_categories.adapter = categories?.let { CategoriesAdapter(categories, ImageEngine) }
        swp_requests.isRefreshing = false
        swp_requests.isEnabled = false
    }

    private fun setupUIRefreshing() {
        swp_requests.isRefreshing = true
    }

    private fun setupUIWithError() {
        swp_requests.isRefreshing = false
        swp_requests.isEnabled = false
        Snackbar.make(
            requireActivity().mainCoordinatorLayout,
            R.string.something_unexpected_happened_try_again_later,
            Snackbar.LENGTH_LONG
        ).show()
    }
}