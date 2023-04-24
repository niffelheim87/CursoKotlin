package com.ompava.doglist_retrofit2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    // Metodo GET en el que le pasamos una url y devolvemos un dataclass DogResponse
    @GET
    fun getDogsByBreeds(@Url url:String): Response<DogResponse>
}