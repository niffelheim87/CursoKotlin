package com.ompava.seriesrecyclergraph.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.snackbar.Snackbar
import com.ompava.seriesrecyclergraph.OnItemClick
import com.ompava.seriesrecyclergraph.R
import com.ompava.seriesrecyclergraph.databinding.ActivityMainBinding
import com.ompava.seriesrecyclergraph.model.Serie

class MainActivity : AppCompatActivity(), OnItemClick {

    private lateinit var binding: ActivityMainBinding
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el diseño de la actividad y lo establece como el contenido de la actividad
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera la posición guardada del Bundle de guardado de instancia
        currentPosition = savedInstanceState?.getInt("position", 0) ?: 0

        // Carga los Fragments necesarios

        loadFragments()


    }

    // Verifica si la orientación actual del dispositivo es apaisada (landscape)
    private fun isLandscape(): Boolean {
        return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    private fun isTablet(): Boolean {

        if (resources.getBoolean(R.bool.isTablet)) {
            return true
        } else {
            return false
        }


    }

    // Carga el fragmento de la lista en el contenedor correspondiente
    private fun loadFragments() {
        loadListFragment()

        // Si la orientación es apaisada, carga el fragmento de detalle
        if (isLandscape()) {
            loadDetailFragment(currentPosition)
        }
    }

    // Carga el fragmento de la lista en el contenedor
    private fun loadListFragment() {
        val containerId = R.id.containerList
        val seriesListFragment = SeriesListFragment.newInstance(1)
        seriesListFragment.listener = this
        supportFragmentManager.beginTransaction()
            .replace(containerId, seriesListFragment)
            .addToBackStack(null)
            .commit()
    }

    // Carga el fragmento de detalle en el contenedor correspondiente
    private fun loadDetailFragment(position: Int) {
        val containerId = if (isLandscape()) R.id.containerDetail else R.id.containerList
        val seriesDetailsFragment = SeriesDetailsFragment.newInstance(position)
        supportFragmentManager.beginTransaction()
            .replace(containerId, seriesDetailsFragment)
            .addToBackStack(null)
            .commit()
    }

    // Guarda el estado actual de la posición en el Bundle de guardado de instancia
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("position", currentPosition)
    }

    // Maneja el evento de clic en un elemento de la lista
    override fun onItemClick(serie: Serie, position: Int) {
        currentPosition = position
        val toastText = "Clicked on ${serie.name}"

        // Si la orientación es apaisada, muestra un Toast
        if (isLandscape()) {
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
            loadDetailFragment(position)
        } else {   // Si no, muestra un Snackbar con una acción para cargar el fragmento de detalle
            val snackbar = Snackbar.make(binding.root, "", Snackbar.LENGTH_LONG)
            snackbar.setAction("Ver detalles de ${serie.name}") {
                loadDetailFragment(position)
            }
            snackbar.show()
        }


    }

}