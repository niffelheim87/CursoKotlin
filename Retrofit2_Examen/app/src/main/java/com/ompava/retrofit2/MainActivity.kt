package com.ompava.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ompava.retrofit2.databinding.ActivityMainBinding
import com.ompava.retrofit2.provider.Provider
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also {binding = it}.root)

        val searchButton: Button = binding.btnSearch
        val searchQueryEditText: EditText = binding.etSearchQuery

        searchButton.setOnClickListener {
            val query = searchQueryEditText.text.toString()
            searchByNumber(query.toInt())
        }

    }



    private fun searchByNumber(user: Int) {
        MainScope().launch {
            val userData = Provider.getUserData(user)

            if (userData != null) {
                // Hacer algo con los datos del usuario
                binding.tvUserName.text = "Nombre: ${userData.first_name} ${userData.last_name}"
                binding.tvUserMail.text = "Email: ${userData.email}"
                Glide.with(applicationContext).load(userData.avatar)//Imagen que vamos a cargar
                    .fitCenter() // Si la imagen es más pequeña se muestra en su tamaño original
                    .dontAnimate() //Evita animaciones
                    .into(binding.ivUserImage)//ImageView
                println("Avatar: ${userData.avatar}")
            } else {
                showError()
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT) .show()
    }
}