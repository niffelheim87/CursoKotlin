package com.ompava.doglist_retrofit2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

// Se define una interfaz Kotlin llamada APIService que define los métodos para hacer llamadas a la API
interface APIService {

    // Se define un método GET llamado getDogsByBreeds que recibe una URL y devuelve un objeto Response
    // que contiene un objeto DogResponse
    @GET
    suspend fun getDogsByBreeds(@Url url:String): Response<DogResponse>
}