package com.ompava.doglist_retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ompava.doglist_retrofit2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                //url para consumir
            .baseUrl("https://dog.ceo/api/breed/")
                //convertir a Gson
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<DogResponse> = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            val puppies:DogResponse? = call.body()
            if (call.isSuccessful){
                // show recyclerview

            } else {
                // show error
            }
        }
    }
}