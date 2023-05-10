package com.ompava.doglist_retrofit2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ompava.doglist_retrofit2.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

// La clase DogViewHolder extiende de RecyclerView.ViewHolder y se utiliza para mantener y actualizar una vista de elemento de RecyclerView
class DogViewHolder(view: View):RecyclerView.ViewHolder(view) {

    // Se inicializa el objeto ItemDogBinding con la vista de elemento de RecyclerView
    private val binding = ItemDogBinding.bind(view)

    // El método bind se llama para actualizar el contenido de la vista de elemento de RecyclerView
    fun bind(image:String){

        // Se carga la imagen de la URL utilizando la biblioteca de carga de imágenes Picasso
        Picasso.get().load(image).into(binding.ivDog)

    }
}