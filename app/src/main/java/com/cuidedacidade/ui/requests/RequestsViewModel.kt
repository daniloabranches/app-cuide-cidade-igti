package com.cuidedacidade.ui.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cuidedacidade.R
import java.util.*

class RequestsViewModel : ViewModel() {
    private val requests: MutableLiveData<List<Request>> by lazy {
        val liveData = MutableLiveData<List<Request>>()
        loadRequests(liveData)
        liveData
    }

    fun getRequests(): LiveData<List<Request>> {
        return requests
    }

    private fun loadRequests(liveData: MutableLiveData<List<Request>>) {
        //TODO Criar modulo de dados para buscar esses dados
        //TODO Remover imagens de drawable
        liveData.value = listOf(
            Request("Coleta", "Teste teste teste teste", R.drawable.ic_recycle.toString(), Date()),
            Request("Iluminação Pública", "Teste teste teste teste", R.drawable.ic_brightness.toString(), Date()),
            Request("Ônibus", "Teste teste teste teste", R.drawable.ic_schooolbus.toString(), Date())
        )
    }
}