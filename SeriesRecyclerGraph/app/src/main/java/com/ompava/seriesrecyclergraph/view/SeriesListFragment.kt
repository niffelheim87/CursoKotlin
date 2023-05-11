package com.ompava.seriesrecyclergraph.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ompava.seriesrecyclergraph.OnItemClick
import com.ompava.seriesrecyclergraph.R
import com.ompava.seriesrecyclergraph.SerieAdapter
import com.ompava.seriesrecyclergraph.databinding.FragmentSeriesListBinding
import com.ompava.seriesrecyclergraph.model.Serie

const val ARG_COLUMN_COUNT = "column-count"

class SeriesListFragment : Fragment() {

    // Declaración de variables
    private lateinit var recyclerView: RecyclerView // Referencia al RecyclerView
    private lateinit var serieAdapter: SerieAdapter // Adaptador para el RecyclerView
    private lateinit var series: MutableList<Serie> // Lista mutable de series
    var listener: OnItemClick? =
        null // Interfaz para manejar los clics en los elementos del RecyclerView
    private var columnCount = 2 // Número de columnas en el GridLayoutManager
    private var binding: FragmentSeriesListBinding? =
        null // Referencia al objeto de ViewBinding del fragmento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {     // Verifica si se han proporcionado argumentos al Fragment.
            columnCount =
                it.getInt(ARG_COLUMN_COUNT)    // Obtiene el valor del argumento "column-count" y lo asigna a la variable columnCount.
        }
        series =
            Serie.getSeries(requireContext()) // Obtiene la lista de series utilizando el método estático getSeries() de la clase Serie, pasando el contexto requerido.
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemClick) {  // Comprueba si el contexto es una instancia de la interfaz OnItemClick.
            listener = context  // Asigna el contexto (actividad) a la variable listener.
        } else {
            throw RuntimeException("$context must implement OnItemClick")  // Lanza una excepción si el contexto no implementa la interfaz OnItemClick.
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null    // Establece la variable listener como nula.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null       // Establece la variable binding como nula.
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Infla el diseño del fragmento
        binding = FragmentSeriesListBinding.inflate(inflater, container, false)
        val view = binding?.root
        // Obtiene la referencia del RecyclerView desde el diseño
        recyclerView = binding?.rvSerieList!!

        // Configura el LayoutManager del RecyclerView
        val layoutManager: RecyclerView.LayoutManager = if (columnCount <= 1) {
            LinearLayoutManager(requireContext())
        } else {
            GridLayoutManager(requireContext(), columnCount)
        }
        recyclerView.layoutManager = layoutManager

        // Configura el adaptador del RecyclerView
        serieAdapter = SerieAdapter(
            series, listener ?: throw RuntimeException("Listener must not be null")
        ) // Pasa la lista de series y la implementación de OnItemClick
        recyclerView.adapter = serieAdapter

        return view
    }

    companion object {
        // Método estático para crear una nueva instancia de SeriesListFragment con argumentos
        @JvmStatic
        fun newInstance(columnCount: Int) = SeriesListFragment().apply {
            // Crea un Bundle para almacenar los argumentos
            arguments = Bundle().apply {
                putInt(ARG_COLUMN_COUNT, columnCount)    // Establece el número de columnas en el argumento
            }
        }
    }
}