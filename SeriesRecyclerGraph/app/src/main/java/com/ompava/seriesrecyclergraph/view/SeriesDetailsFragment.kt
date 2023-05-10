package com.ompava.seriesrecyclergraph.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ompava.seriesrecyclergraph.databinding.FragmentSeriesDetailsBinding
import com.ompava.seriesrecyclergraph.model.Serie

private const val ARG_POSITION = "position"

/**
 * A simple [Fragment] subclass.
 * Use the [SeriesDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SeriesDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var position: Int = -1
    private lateinit var binding: FragmentSeriesDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION, -1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSeriesDetailsBinding.inflate(inflater, container, false)
        if (position == -1) {
            position = savedInstanceState?.getInt("position", 0) ?: arguments?.getInt("position", 0) ?: 0
        }
        val serie = Serie.getSeries(requireContext())[position]

        binding.tvName.text = serie.name
        binding.tvDescription.text = serie.summary


        return binding.root
    }
    


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SeriesDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(position: Int) =
            SeriesDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, position)
                }
            }
    }
}