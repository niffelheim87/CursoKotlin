package com.ompava.masterdetail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ompava.masterdetail.adapter.SuperHeroAdapter
import com.ompava.masterdetail.databinding.FragmentListBinding
import com.ompava.masterdetail.`interface`.OnItemClick
import com.ompava.masterdetail.model.SuperHero
import com.ompava.masterdetail.model.SuperHeroProvider


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    //property that indicates if we want to view a single item list or grid list. Used on onCreateView
    private var columnCount = 2

    //Listener is our callback
    var listener: OnItemClick? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //When fragment is created, "search" on its arguments a bundle with Key ARG_COLUMN_COUNT and set the property above
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }


    }

    //When fragment is attaching to an activity, the context is the activity. Remember MainActivity Implements OnItemclick
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemClick) {
            listener = context
        }
    }

    //Detach the listener. Set it to null
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        initRecyclerView()


        return _binding!!.root
    }

    private fun initRecyclerView() {

        val layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }

        _binding?.recyclerSuperHero?.layoutManager = layoutManager
        _binding?.recyclerSuperHero?.adapter =
            SuperHeroAdapter(SuperHeroProvider.superHeroList) { superHero ->
                onItemSelected(
                    superHero
                )
            }

    }

    private fun onItemSelected(superHero: SuperHero) {
        Toast.makeText(context, superHero.superhero, Toast.LENGTH_SHORT).show()
    }

    //Creating an static method to simulate factory pattern. Creates a new ListFragment, and sets countColumn param through bundle.
    companion object {
        private const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

}