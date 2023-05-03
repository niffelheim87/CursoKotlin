package com.ompava.myrecycler

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ompava.myrecycler.databinding.SerieDetailsBinding
import com.ompava.myrecycler.models.SerieManager


class DetailsSerie : Fragment() {

    private lateinit var binding: SerieDetailsBinding
    private lateinit var serieManager: SerieManager
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serieManager = SerieManager(requireContext())
        position = arguments?.getInt("position", 0) ?: savedInstanceState?.getInt("position", 0) ?: 0
        Log.d("Bundle Value", arguments?.getInt("position").toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = SerieDetailsBinding.inflate(inflater, container, false)

        // Usar el índice de posición para bindear las vistas correspondientes
        if (position == -1) {
            position = savedInstanceState?.getInt("position", 0) ?: arguments?.getInt("position", 0) ?: 0
        }
        val serie = serieManager.getSeries()[position]
        binding.tvName.text = serie.name
        binding.tvDescription.text = serie.summary

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("position", position)
    }

}