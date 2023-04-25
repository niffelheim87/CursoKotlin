package com.ompava.examplemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ompava.examplemvvm.model.QuoteModel
import com.ompava.examplemvvm.model.QuoteProvider

class QuoteViewModel: ViewModel() {

    // LiveData que expondrá los datos del usuario a la vista
    val quoteModel = MutableLiveData<QuoteModel>()

    // Función que obtiene los datos del usuario y los actualiza en el LiveData
    fun randomQuote(){
        // Lógica para obtener los datos del usuario de la capa de datos
        val currentQuote = QuoteProvider.random()
        quoteModel.postValue(currentQuote)
    }
}