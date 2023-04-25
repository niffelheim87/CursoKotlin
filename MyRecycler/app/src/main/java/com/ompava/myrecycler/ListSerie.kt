package com.ompava.myrecycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ompava.myrecycler.adapters.SerieAdapter
import com.ompava.myrecycler.databinding.SerieListBinding
import com.ompava.myrecycler.models.SerieManager


class ListSerie : Fragment() {


    private lateinit var binding: SerieListBinding
    private lateinit var serieManager: SerieManager

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
        val serieAdapter = SerieAdapter(serieManager.getSeries())
        recyclerView.adapter = serieAdapter
        
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