package com.cuidedacidade.ui.requestdetails

import com.cuidedacidade.base.BaseViewModel
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.usecase.SaveRequestUseCase
import com.cuidedacidade.security.Auth
import com.cuidedacidade.ui.categories.model.CategoryModel
import java.util.*
import javax.inject.Inject

class RequestDetailsViewModel @Inject constructor(
    private val saveRequestUseCase: SaveRequestUseCase
) : BaseViewModel() {
    fun saveRequest(category: CategoryModel, description: String) {
        saveRequestUseCase(
            Auth.USER,
            Request("", category.title, description, category.image, Date(), Request.Status.PENDING)
        )
    }
}