package com.ompava.myrecycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ompava.myrecycler.adapters.SerieAdapter
import com.ompava.myrecycler.databinding.SerieListBinding
import com.ompava.myrecycler.models.Serie
import com.ompava.myrecycler.models.SerieManager


class ListSerie : Fragment() {
    // Declaramos, binding y serieManager como propiedades como propiedad de la clase
    private lateinit var serieAdapter: SerieAdapter
    private lateinit var binding: SerieListBinding
    private lateinit var serieManager: SerieManager

    // Definimos un listener que se encargará de manejar el evento onItemClick del adapter
    val listener = object : OnItemClickListener {
        override fun onItemClick(serie: Serie) {
            // Obtenemos la posición de la serie que se ha pulsado y mostramos un Toast
            val toastText = "Clicked on item ${getPosition(serie)}"
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
        }
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
        serieManager = SerieManager(requireContext())
        serieAdapter = SerieAdapter(serieManager.getSeries(), listener)
        recyclerView.adapter = serieAdapter

        // Devolver la vista inflada
        return binding.root
    }

    companion object {
        private const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ListSerie().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }

            }
    }
}