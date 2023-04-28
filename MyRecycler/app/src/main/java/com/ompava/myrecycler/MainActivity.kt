package com.ompava.myrecycler

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ompava.myrecycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar el diseño de la actividad usando el enlace de datos generado
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Establecer la vista raíz de la actividad en la vista raíz del diseño de enlace de datos
        setContentView(binding.root)

        // Obtener la orientación actual de la pantalla
        val currentOrientation = resources.configuration.orientation

        // Si la orientación es horizontal (paisaje), crear y mostrar dos fragmentos (lista y detalle)
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Crear dos fragmentos
            val listSerieFragment = ListSerie()
            val detailsSerieFragment = DetailsSerie()
            // Reemplazar los contenedores de fragmentos en el diseño con los fragmentos creados
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.containerList, listSerieFragment)
                replace(R.id.containerDetail, detailsSerieFragment)
                commit()
            }

        } else {  // Si la orientación es vertical (retrato), crear y mostrar solo un fragmento (lista)
            // Crear un fragmento
            val listSerieFragment = ListSerie()
            // Reemplazar el contenedor de fragmentos en el diseño con el fragmento creado
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.containerList, listSerieFragment)
                commit()
            }

        }


    }


}