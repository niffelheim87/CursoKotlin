package com.ompava.recycler_viewmodel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ompava.recycler_viewmodel.`interface`.APIService
import com.ompava.recycler_viewmodel.model.DogResponse
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DogViewModel : ViewModel() {
    val itemsLiveData = MutableLiveData<List<String>>()

    private fun getRetrofit(): Retrofit {
        // Creamos un objeto Retrofit para interactuar con la API de perros
        return Retrofit.Builder()
            // Establecemos la URL base para la API
            .baseUrl("https://dog.ceo/api/breed/")
            // Usamos Gson para convertir la respuesta JSON en objetos Kotlin
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun searchByName(query: String){
        val retrofit = getRetrofit()
        val apiService = retrofit.create(APIService::class.java)

        viewModelScope.launch {
            try {
                val call = apiService.getDogsByBreeds("$query/images")
                if (call.isSuccessful) {
                    val puppies:DogResponse? = call.body()
                    val images: List<String> = puppies?.images ?: emptyList()
                    itemsLiveData.value = images
                } else {
                    // Manejar error de la API
                }
            } catch (e: Exception) {
                // Manejar error de conexión u otros errores
            }
        }
    }



}