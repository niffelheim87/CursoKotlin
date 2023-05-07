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

private const val ARG_POSITION = "position"

class ListSerie : Fragment() {
    // Declaramos, binding y serieManager como propiedades como propiedad de la clase
    private lateinit var serieAdapter: SerieAdapter
    private lateinit var binding: SerieListBinding
    private lateinit var serieManager: SerieManager
    private var position: Int? = null
    var bundle: Bundle? = null
    var detailFragment: DetailSerie? = null

    // Definimos un listener que se encargará de manejar el evento onItemClick del adapter
    private val listener = object : OnItemClickListener {
        override fun onItemClick(serie: Serie) {
            // Obtenemos la posición de la serie que se ha pulsado y mostramos un Toast
            position = getPosition(serie)
            val currentOrientation = resources.configuration.orientation
            bundle = Bundle().apply {
                putInt("position", position!!)
            }
            Log.d("Bundle Value", "Bundle toma el valor por listener de: " + bundle?.getInt("position").toString())

            detailFragment = DetailSerie().apply {
                arguments = bundle
            }

            val toastText = "Clicked on ${serieManager.getSeries()[position!!].name}"

            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.containerDetail, detailFragment!!)
                    .addToBackStack(null)
                    .commit()

            } else {
                val snackbar = Snackbar.make(binding.root, toastText, Snackbar.LENGTH_LONG)
                snackbar.setAction("Ver detalles") {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.containerList, detailFragment!!).addToBackStack(null).commit()
                }
                snackbar.show()
            }


        }
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        bundle.putInt(ARG_POSITION, position ?: 0)

        Log.d(
            "Bundle Value",
            "La posicion guardad en el bundle de ListSerie es: " + bundle.getInt("position")
                .toString()
        )
    }



    // Función auxiliar que devuelve la posición de una serie dentro del array de series
    fun getPosition(serie: Serie): Int {
        return serieManager.getSeries().indexOf(serie)
    }

    // Función que se encarga de inflar la vista del fragmento y configurar el RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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

        // Devolver la vista inflada
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        onSaveInstanceState(bundle!!)
        bundle = null
        detailFragment = null
        Log.d(
            "Bundle Value",
            "al cambiar de orientation el Bundle de ListSerie es: " + bundle.toString()
        )
    }
}