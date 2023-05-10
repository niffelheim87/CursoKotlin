package com.ompava.examplemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ompava.examplemvvm.data.model.QuoteModel
import com.ompava.examplemvvm.data.model.QuoteProvider
import com.ompava.examplemvvm.domain.GetQuotesUseCase
import kotlinx.coroutines.launch

class QuoteViewModel: ViewModel() {

    // LiveData que expondrá los datos del usuario a la vista
    val quoteModel = MutableLiveData<QuoteModel>()
    val isLoading = MutableLiveData<Boolean>()

    var getQuotesUseCase = GetQuotesUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result:List<QuoteModel>? = getQuotesUseCase()

            if(!result.isNullOrEmpty()){
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    // Función que obtiene los datos del usuario y los actualiza en el LiveData
    fun randomQuote(){
        isLoading.postValue(true)
        // Lógica para obtener los datos del usuario de la capa de datos
       // val currentQuote = QuoteProvider.random()
       // quoteModel.postValue(currentQuote)
        isLoading.postValue(false)
    }


}