package com.ompava.seriesrecyclergraph.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ompava.seriesrecyclergraph.databinding.FragmentSeriesDetailsBinding
import com.ompava.seriesrecyclergraph.model.Serie

private const val ARG_POSITION = "position"

class SeriesDetailsFragment : Fragment() {

    private var position: Int = -1
    private lateinit var binding: FragmentSeriesDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION, -1) // Obtiene la posición del argumento
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Infla el diseño del fragmento
        binding = FragmentSeriesDetailsBinding.inflate(inflater, container, false)
        if (position == -1) {
            // Verifica si la posición es -1, si es así, intenta obtenerla del estado guardado o de los argumentos
            position =
                savedInstanceState?.getInt("position", 0) ?: arguments?.getInt("position", 0) ?: 0
            // Si no se encuentra la posición en el estado guardado o los argumentos, se establece en 0 por defecto

        }
        val serie = Serie.getSeries(requireContext())[position]  // Obtiene la serie correspondiente a la posición

        // Configura los datos de la serie en las vistas correspondientes
        binding.tvName.text = serie.name
        binding.tvDescription.text = serie.summary
        val context = binding.root.context
        val imageName = serie.image.substringBeforeLast(".")
        val id = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        binding.ivImage.setImageResource(id)


        return binding.root     // Retorna la vista raíz del fragmento
    }


    companion object {

        @JvmStatic
        fun newInstance(position: Int) = SeriesDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_POSITION, position)    // Establece la posición en los argumentos
            }
        }
    }
}