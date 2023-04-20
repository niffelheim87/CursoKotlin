package com.ompava.masterdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ompava.masterdetail.R
import com.ompava.masterdetail.`interface`.OnItemClick
import com.ompava.masterdetail.model.SuperHero

class SuperHeroAdapter(
    private val superHeroList: List<SuperHero>,
    private var listener: OnItemClick
) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(
            layoutInflater.inflate(R.layout.fragment_item, parent, false),
            listener
        )
    }

    override fun getItemCount(): Int = superHeroList.size


    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { 
            listener?.onItemClick(item)
        }
    }

}