package com.ompava.masterdetail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ompava.masterdetail.R
import com.ompava.masterdetail.databinding.FragmentItemBinding
import com.ompava.masterdetail.`interface`.OnItemClick
import com.ompava.masterdetail.model.SuperHero

class SuperHeroViewHolder(view: View, private var listener: OnItemClick) :
    RecyclerView.ViewHolder(view), View.OnClickListener {

    private val binding = FragmentItemBinding.bind(view)


    fun render(superHeroModel: SuperHero) {
        val context = binding.root.context
        binding.tvSuperhero.text = superHeroModel.superhero
        binding.tvRealName.text = superHeroModel.realName
        binding.tvPublisher.text = superHeroModel.publisher
        val imageName = superHeroModel.image
        val id = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        binding.ivPoster.setImageResource(id)
        //Glide.with(binding.ivPoster.context).load(superHeroModel.photo).into(binding.ivPoster)
        //binding.root.tag = superHeroModel
        binding.root.setOnClickListener{
            listener.onItemClick(superHeroModel)
        }
    }

    override fun toString(): String {
        return super.toString() + " ${binding.tvRealName.text} is ${binding.tvSuperhero.text} from ${binding.tvPublisher.text}"
    }

    override fun onClick(v: View?) {

        //First we recovery de superHero through the tag. Remember we have set it before (onBindViewHolder)
        val superHero = v?.tag as SuperHero

        //later we call our callback with the SuperHero param to inform ListFragment, and then MainActivity
        listener.onItemClick(superHero)
    }
}

