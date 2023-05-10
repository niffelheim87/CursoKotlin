package com.ompava.myrecycler.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ompava.myrecycler.OnItemClick
import com.ompava.myrecycler.R
import com.ompava.myrecycler.models.Serie

// Clase SerieAdapter, responsable de crear y mantener los elementos del RecyclerView
class SerieAdapter(private val dataset: List<Serie>, private val listener: OnItemClick) : RecyclerView.Adapter<SerieViewHolder>() {

    // onCreateViewHolder se llama cuando se necesita crear un nuevo ViewHolder para el RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        // Crear un View inflando el diseño de la vista de cada elemento del RecyclerView
        val view = LayoutInflater.from(parent.context).inflate(R.layout.serie_item, parent, false)
        // Crear un ViewHolder usando la vista creada anteriormente
        return SerieViewHolder(view)
    }

    // onBindViewHolder se llama para actualizar el contenido del ViewHolder en una posición específica en el RecyclerView
    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        // Llamar al método bind del ViewHolder para establecer los valores de los elementos de la vista
        holder.bind(dataset[position], listener)
    }

    // getItemCount se llama para obtener el número de elementos en el conjunto de datos (dataset) del adaptador
    override fun getItemCount() = dataset.size
}