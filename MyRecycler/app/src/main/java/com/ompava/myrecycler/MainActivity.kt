package com.ompava.myrecycler

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ompava.myrecycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Crea una instancia de la clase ActivityMainBinding que se inicializará más adelante.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityMainBinding.inflate(layoutInflater) // Infla el diseño ActivityMainBinding.
        setContentView(binding.root) // Establece la vista raíz del diseño como la vista de contenido de la actividad.

        val listSerieFragment = ListSerie() // Crea una instancia del fragmento ListSerie.
        supportFragmentManager.beginTransaction().apply { // Inicia una transacción de fragmento.
            replace(
                R.id.containerList,
                listSerieFragment
            ) // Reemplaza el contenido del contenedor de fragmentos "containerList" con el fragmento ListSerie.
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) { // Verifica si la orientación de la pantalla es horizontal.
                val detailsSerieFragment =
                    DetailSerie() // Si es así, crea una instancia del fragmento DetailsSerie.
                replace(
                    R.id.containerDetail,
                    detailsSerieFragment
                ) // Reemplaza el contenido del contenedor de fragmentos "containerDetail" con el fragmento DetailsSerie.
            }
            commit() // Confirma la transacción de fragmento.
        }
    }
}


