package com.ompava.myrecycler

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ompava.myrecycler.databinding.SerieDetailBinding
import com.ompava.myrecycler.models.SerieManager

private const val ARG_POSITION = "position"

class DetailSerie : Fragment() {

    private lateinit var binding: SerieDetailBinding
    private lateinit var serieManager: SerieManager
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serieManager = SerieManager(requireContext())

        position =
            arguments?.getInt("position", 0) ?: 0


        Log.d(
            "Bundle Value",
            "Valor tomado por arguments en DetailsSerie: " + arguments?.getInt("position")
                .toString()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (savedInstanceState != null) {
            Log.d(
                "Bundle Value",
                "Valor tomado por SavedInstanceState en DetailsSerie: " + savedInstanceState.getInt(
                    "position"
                )
                    .toString()
            )
            position = savedInstanceState.getInt("position", -1)
        }

        // Inflate the layout for this fragment
        binding = SerieDetailBinding.inflate(inflater, container, false)
        
        val serie = serieManager.getSeries()[position]
        binding.tvName.text = serie.name
        binding.tvDescription.text = serie.summary

        return binding.root
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt(ARG_POSITION, position)
    }

    companion object {
        @JvmStatic
        fun newInstance(position: Int) =
            DetailSerie().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, position)
                }
            }
    }

}