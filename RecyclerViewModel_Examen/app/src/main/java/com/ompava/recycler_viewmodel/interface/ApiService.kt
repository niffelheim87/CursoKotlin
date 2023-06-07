package com.ompava.recycler_viewmodel.`interface`

import com.ompava.recycler_viewmodel.model.DogResponse
import retrofit2.Response
import retrofit2.http.GET

import retrofit2.http.Url

interface APIService {

    // Se define un método GET llamado getDogsByBreeds que recibe una URL y devuelve un objeto Response
    // que contiene un objeto DogResponse
    @GET
    suspend fun getDogsByBreeds(@Url url:String): Response<DogResponse>
}