package com.ompava.myrecycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ompava.myrecycler.databinding.SerieDetailsBinding
import com.ompava.myrecycler.databinding.SerieListBinding
import com.ompava.myrecycler.models.SerieManager


class DetailsSerie : Fragment() {

    private lateinit var binding: SerieDetailsBinding
    private lateinit var serieManager: SerieManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = SerieDetailsBinding.inflate(inflater, container, false)

        serieManager = SerieManager(requireContext())
        binding.tvName.text = serieManager.getSerieById(0).name
        binding.tvDescription.text = serieManager.getSerieById(0).summary

        return binding.root
    }


}