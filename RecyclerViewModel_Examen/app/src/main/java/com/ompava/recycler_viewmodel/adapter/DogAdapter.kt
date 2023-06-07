package com.ompava.recycler_viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ompava.recycler_viewmodel.R
import com.ompava.recycler_viewmodel.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class DogAdapter(private val items: List<String>) : RecyclerView.Adapter<DogAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)

        fun bind(item: String) {
            Picasso.get().load(item).into(binding.ivDog)

        }
    }
}