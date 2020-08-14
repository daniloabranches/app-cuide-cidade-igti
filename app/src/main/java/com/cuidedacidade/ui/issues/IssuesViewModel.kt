package com.cuidedacidade.ui.issues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cuidedacidade.R

class IssuesViewModel : ViewModel() {
    private val issues: MutableLiveData<List<Issue>> by lazy {
        MutableLiveData<List<Issue>>().also {
            val issues = loadIssues()
            it.value = issues
        }
    }

    fun getIssues(): LiveData<List<Issue>> {
        return issues
    }

    private fun loadIssues(): List<Issue> {
        //TODO Criar modulo de dados para buscar esses dados
        //TODO Remover imagens de drawable
        return listOf(
            Issue("Coleta", "Teste teste teste teste", R.drawable.ic_recycle.toString()),
            Issue("Iluminação Pública", "Teste teste teste teste", R.drawable.ic_brightness.toString()),
            Issue("Ônibus", "Teste teste teste teste", R.drawable.ic_schooolbus.toString())
        )
    }
}