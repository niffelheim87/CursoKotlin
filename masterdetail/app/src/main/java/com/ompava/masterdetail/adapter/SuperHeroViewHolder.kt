package com.ompava.masterdetail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ompava.masterdetail.databinding.FragmentItemBinding
import com.ompava.masterdetail.model.SuperHero

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = FragmentItemBinding.bind(view)


    fun render(superHeroModel: SuperHero, onClickListener: (SuperHero) -> Unit) {
        binding.tvSuperhero.text = superHeroModel.superhero
        binding.tvRealName.text = superHeroModel.realName
        binding.tvPublisher.text = superHeroModel.publisher
        Glide.with(binding.ivPoster.context).load(superHeroModel.photo).into(binding.ivPoster)
        itemView.setOnClickListener { onClickListener(superHeroModel) }
    }

}