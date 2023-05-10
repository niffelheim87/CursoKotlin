package com.ompava.seriesrecyclergraph.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ompava.seriesrecyclergraph.OnItemClick
import com.ompava.seriesrecyclergraph.R
import com.ompava.seriesrecyclergraph.SerieAdapter
import com.ompava.seriesrecyclergraph.model.Serie

private const val ARG_POSITION = "position"


/**
 * A simple [Fragment] subclass.
 * Use the [SeriesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SeriesListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var positon: Int? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var serieAdapter: SerieAdapter
    private lateinit var series: List<Serie>
    var listener: OnItemClick? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*arguments?.let {
            positon = it.getInt(ARG_POSITION)
        } */
        series = Serie.getSeries(requireContext())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verifica que la actividad contenedora implemente la interfaz OnItemClick
        if (context is OnItemClick) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnItemClick")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_series_list, container, false)

        // Obtén la referencia del RecyclerView desde el layout
        recyclerView = view.findViewById(R.id.rvSerieList)

        // Configura el LayoutManager del RecyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        // Configura el adaptador del RecyclerView
        serieAdapter = SerieAdapter(series, listener!!) // Pasa la lista de series y la implementación de OnItemClick
        recyclerView.adapter = serieAdapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SeriesListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(position: Int) =
            SeriesListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, position)
                }
            }
    }
}