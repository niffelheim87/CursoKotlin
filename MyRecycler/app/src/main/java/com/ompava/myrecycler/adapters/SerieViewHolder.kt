package com.ompava.myrecycler.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ompava.myrecycler.databinding.SerieItemBinding
import com.ompava.myrecycler.models.Serie


class SerieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val binding = SerieItemBinding.bind(view)

    fun bind(serie: Serie) {
        binding.tvName.text = serie.name
        binding.tvLanguage.text = serie.language
        binding.rbRating.rating = serie.rating.toFloat() / 2
        //view.findViewById<ImageView>(R.id.ivImage).setImageResource(serie.image.toInt())
    }
}