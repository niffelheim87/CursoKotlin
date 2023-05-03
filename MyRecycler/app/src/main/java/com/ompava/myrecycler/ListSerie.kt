package com.ompava.myrecycler

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ompava.myrecycler.adapters.SerieAdapter
import com.ompava.myrecycler.databinding.SerieListBinding
import com.ompava.myrecycler.models.Serie
import com.ompava.myrecycler.models.SerieManager


class ListSerie : Fragment() {
    // Declaramos, binding y serieManager como propiedades como propiedad de la clase
    private lateinit var serieAdapter: SerieAdapter
    private lateinit var binding: SerieListBinding
    private lateinit var serieManager: SerieManager
    private var currentPosition: Int = -1

    // Definimos un listener que se encargará de manejar el evento onItemClick del adapter
    val listener = object : OnItemClickListener {
        override fun onItemClick(serie: Serie) {
            // Obtenemos la posición de la serie que se ha pulsado y mostramos un Toast
            val position = getPosition(serie)
            currentPosition = position
            val currentOrientation = resources.configuration.orientation
            val bundle = Bundle()
            bundle.putInt("position", position)

            val fragment = DetailsSerie()
            fragment.arguments = bundle
            val toastText = "Clicked on ${serieManager.getSeries()[position].name}"
            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.containerDetail, fragment)
                    .addToBackStack(null)
                    .commit()


            } else {
                val snackbar = Snackbar.make(binding.root, toastText, Snackbar.LENGTH_LONG)
                snackbar.setAction("Ver detalles") {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.containerList, fragment)
                        .addToBackStack(null)
                        .commit()
                }
                snackbar.show()
            }


        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("position", currentPosition)
    }


    // Función auxiliar que devuelve la posición de una serie dentro del array de series
    fun getPosition(serie: Serie): Int {
        return serieManager.getSeries().indexOf(serie)
    }

    // Función que se encarga de inflar la vista del fragmento y configurar el RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el diseño del fragmento usando ViewBinding
        binding = SerieListBinding.inflate(inflater, container, false)

        // Obtener la referencia del RecyclerView y configurar el administrador de diseño
        val recyclerView = binding.rvSerieList
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Crear una instancia de SerieAdapter y configurarla en el RecyclerView
        serieManager = SerieManager(requireActivity())
        serieAdapter = SerieAdapter(serieManager.getSeries(), listener)
        recyclerView.adapter = serieAdapter

        // Verificar si hay un valor guardado en el bundle y configurar la posición actual si se encuentra
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("position", -1)
        }

        // Devolver la vista inflada
        return binding.root
    }


}