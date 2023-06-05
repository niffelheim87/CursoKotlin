package com.ompava.retrofit2.provider

import com.ompava.retrofit2.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("api/users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<UserResponse>
}
