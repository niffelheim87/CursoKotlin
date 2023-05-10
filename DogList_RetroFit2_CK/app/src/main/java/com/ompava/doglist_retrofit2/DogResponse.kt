package com.ompava.doglist_retrofit2

import com.google.gson.annotations.SerializedName

// Se define una clase de datos Kotlin llamada DogResponse, que representa la respuesta de la API
data class DogResponse(

    // Se usa la anotación @SerializedName para indicar que el nombre de la propiedad en la clase
    // es diferente del nombre del campo JSON en la respuesta de la API
    @SerializedName("status") var status:String,

    // Se define otra propiedad en la clase, que se llama "images" y que es una lista de cadenas
    @SerializedName("message") var images:List<String>
    )