package com.ompava.volley.provider

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ompava.volley.model.UserResponse
import com.ompava.volley.model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val BASE_URL = "https://reqres.in"

object Provider {

    suspend fun getUserData(context: Context, user: Int, onLoad:(userData:UserData?)->Unit): UserData? = withContext(Dispatchers.IO) {
        val url = "$BASE_URL/api/users/$user"
        var userData: UserData? = null

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val userResponse = Gson().fromJson(response.toString(), UserResponse::class.java)
                userData = userResponse.data
                Log.d("VOLLEY", "EXITO")
                onLoad(userData)
            },
            { error ->
                println("Error: ${error.message}")
                Log.d("VOLLEY", "ERROR")
                onLoad(userData)
            }
        )

        val queue = Volley.newRequestQueue(context)
        queue.add(request)

        userData
    }
}
