package com.ompava.myrecycler.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ompava.myrecycler.OnItemClickListener
import com.ompava.myrecycler.databinding.SerieItemBinding
import com.ompava.myrecycler.models.Serie

// Clase SerieViewHolder, responsable de mantener las vistas para cada elemento en el RecyclerView
class SerieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // Obtener una instancia del binding de la vista utilizando la clase SerieItemBinding
    private val binding = SerieItemBinding.bind(view)

    // Método para actualizar el contenido de las vistas con los valores de la serie correspondiente
    fun bind(serie: Serie, listener: OnItemClickListener) {
        // Establecer el nombre de la serie en el TextView correspondiente
        binding.tvName.text = serie.name
        // Establecer el idioma de la serie en el TextView correspondiente
        binding.tvLanguage.text = serie.language
        // Establecer la calificación de la serie en el RatingBar correspondiente
        binding.rbRating.rating = serie.rating.toFloat() / 2
        //view.findViewById<ImageView>(R.id.ivImage).setImageResource(serie.image.toInt())

        // Establecer un listener en la vista raíz del binding para detectar cuando el usuario haga clic en el elemento del RecyclerView
        binding.root.setOnClickListener { listener.onItemClick(serie) }


    }
}