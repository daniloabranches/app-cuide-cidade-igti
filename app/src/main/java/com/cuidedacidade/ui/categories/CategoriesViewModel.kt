package com.cuidedacidade.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoriesViewModel : ViewModel() {

    private val categories: MutableLiveData<List<Category>> by lazy {
        MutableLiveData<List<Category>>().also {
            val categories = loadCategories()
            it.value = categories
        }
    }

    fun getCategories(): LiveData<List<Category>> {
        return categories
    }

    private fun loadCategories(): List<Category> {
        return listOf(
            Category("Ônibus", "ic_schooolbus.png"),
            Category("Coleta", "ic_recycle.png"),
            Category("Iluminação Pública", "ic_brightness.png"),
            Category("Táxi", "ic_taxi.png"),
            Category("Pavimentação", "ic_tractor.png"),
            Category("Segurança", "ic_security.png"),
            Category("Semáforo", "ic_traffic.png"),
            Category("Telefones", "ic_phone.png")
        )
    }
}