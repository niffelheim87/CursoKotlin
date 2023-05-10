package com.ompava.seriesrecyclergraph

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ompava.seriesrecyclergraph.databinding.SerieItemBinding
import com.ompava.seriesrecyclergraph.model.Serie

class SerieAdapter(private val series: List<Serie>, private val listener: OnItemClick?) : RecyclerView.Adapter<SerieAdapter.SerieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.serie_item, parent, false)
        return SerieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val serie = series[position]
        listener?.let { holder.bind(serie, it) }
    }

    override fun getItemCount(): Int {
        return series.size
    }

    inner class SerieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = SerieItemBinding.bind(itemView)

        fun bind(serie: Serie, listener: OnItemClick) {
            val position = adapterPosition
            // Establecer el nombre de la serie en el TextView correspondiente
            binding.tvName.text = serie.name
            // Establecer el idioma de la serie en el TextView correspondiente
            binding.tvLanguage.text = serie.language
            // Establecer la calificación de la serie en el RatingBar correspondiente
            binding.rbRating.rating = serie.rating.toFloat() / 2
            // Establecer un listener en la vista raíz del binding para detectar cuando el usuario haga clic en el elemento del RecyclerView
            binding.root.setOnClickListener { listener.onItemClick(serie, position) }
        }


    }
}