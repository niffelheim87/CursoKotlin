package com.ompava.retrofit2.provider

import com.ompava.retrofit2.model.UserData
import com.ompava.retrofit2.model.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://reqres.in/"

object Provider {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getUserData(user: Int): UserData? = withContext(Dispatchers.IO) {
        val call = getRetrofit().create(APIService::class.java).getUser(user)

        if (call.isSuccessful) {
            val userResponse: UserResponse? = call.body()
            return@withContext userResponse?.data
        }

        return@withContext null
    }


}