package com.ompava.seriesrecyclergraph

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ompava.seriesrecyclergraph.databinding.SerieItemBinding
import com.ompava.seriesrecyclergraph.model.Serie

class SerieAdapter(private val series: List<Serie>, private val listener: OnItemClick?) : RecyclerView.Adapter<SerieAdapter.SerieViewHolder>() {


    // Crea y devuelve una instancia de SerieViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.serie_item, parent, false)
        return SerieViewHolder(itemView)
    }

    // Vincula los datos de la serie en la posición dada con el ViewHolder
    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val serie = series[position]
        listener?.let { holder.bind(serie, it) }
    }

    // Devuelve la cantidad de elementos en la lista de series
    override fun getItemCount(): Int {
        return series.size
    }

    inner class SerieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = SerieItemBinding.bind(itemView)

        // Vincula los datos de la serie y el listener con las vistas correspondientes en el ViewHolder
        fun bind(serie: Serie, listener: OnItemClick) {
            val position = adapterPosition

            // Establecer el nombre de la serie en el TextView correspondiente
            binding.tvName.text = serie.name

            // Establecer el idioma de la serie en el TextView correspondiente
            binding.tvLanguage.text = serie.language

            // Establecer la calificación de la serie en el RatingBar correspondiente
            binding.rbRating.rating = serie.rating.toFloat() / 2

            //
            val context = binding.root.context
            val imageName = serie.image.substringBeforeLast(".")
            val id = context.resources.getIdentifier(imageName, "drawable", context.packageName)
            binding.ivImage.setImageResource(id)

            // Establecer un listener en la vista raíz del binding para detectar cuando el usuario haga clic en el elemento del RecyclerView
            binding.root.setOnClickListener { listener.onItemClick(serie, position) }
        }




    }
}