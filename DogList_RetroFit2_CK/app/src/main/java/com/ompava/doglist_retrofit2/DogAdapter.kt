package com.ompava.doglist_retrofit2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// Se define una clase DogAdapter que extiende de RecyclerView.Adapter y se utiliza para manejar la lista de imágenes de perros
class DogAdapter(private val images:List<String>):RecyclerView.Adapter<DogViewHolder>() {

    // onCreateViewHolder se llama cuando se necesita crear una nueva instancia de DogViewHolder para mostrar un elemento
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {

        // Se infla el diseño de vista de elemento de RecyclerView utilizando el objeto LayoutInflater
        val layoutInflater = LayoutInflater.from(parent.context)
        // Se crea y devuelve una nueva instancia de DogViewHolder
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    // getItemCount devuelve el número de elementos en la lista
    override fun getItemCount(): Int = images.size

    // onBindViewHolder se llama cuando se necesita actualizar el contenido de una vista de elemento existente
    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        // Se obtiene la imagen de perro en la posición actual
        val item:String = images[position]
        // Se llama al método bind del DogViewHolder para actualizar el contenido de la vista de elemento
        holder.bind(item)
    }
}