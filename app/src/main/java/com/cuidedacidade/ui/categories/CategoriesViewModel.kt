package com.cuidedacidade.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cuidedacidade.R

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
        //TODO Criar modulo de dados para buscar esses dados
        //TODO Remover imagens de drawable
        return listOf(
            Category("Coleta", R.drawable.ic_recycle.toString()),
            Category("Iluminação Pública", R.drawable.ic_brightness.toString()),
            Category("Ônibus", R.drawable.ic_schooolbus.toString()),
            Category("Pavimentação", R.drawable.ic_tractor.toString()),
            Category("Segurança", R.drawable.ic_security.toString()),
            Category("Semáforo", R.drawable.ic_traffic.toString()),
            Category("Táxi", R.drawable.ic_taxi.toString()),
            Category("Telefones", R.drawable.ic_phone.toString())
        )
    }
}