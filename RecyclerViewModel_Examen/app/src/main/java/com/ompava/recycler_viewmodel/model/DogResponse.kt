package com.ompava.recycler_viewmodel.model

import com.google.gson.annotations.SerializedName

data class DogResponse(

    // Se usa la anotación @SerializedName para indicar que el nombre de la propiedad en la clase
    // es diferente del nombre del campo JSON en la respuesta de la API
    @SerializedName("status") var status:String,

    // Se define otra propiedad en la clase, que se llama "images" y que es una lista de cadenas
    @SerializedName("message") var images:List<String>
)