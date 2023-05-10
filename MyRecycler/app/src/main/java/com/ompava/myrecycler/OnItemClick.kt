package com.ompava.myrecycler

import com.ompava.myrecycler.models.Serie

// Interfaz que define un método para ser implementado por la clase que maneja el evento de click en el adaptador
// Recibe como parámetro un objeto de la clase Serie, que representa el elemento del adaptador que ha sido clickeado
interface OnItemClick {
    fun onItemClick(serie: Serie)

}