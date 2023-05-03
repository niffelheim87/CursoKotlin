package com.ompava.myrecycler.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ompava.myrecycler.R
import java.io.BufferedReader
import java.io.InputStreamReader

class SerieManager(context: Context) {
    private val gson = Gson()
    val SerieList: MutableList<Serie>

    init {
        // Leer archivo JSON desde la carpeta /res/raw
        val inputStream = context.resources.openRawResource(R.raw.datos_json)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val json = bufferedReader.use { it.readText() }

        // Convertir el JSON a una lista de objetos Serie utilizando la biblioteca Gson
        val listType = object : TypeToken<List<Serie>>() {}.type
        SerieList = gson.fromJson(json, listType)
    }

    fun getSerieById(mId: Int?): Serie {
        val serie = getSeries().filter { serie ->
            serie.id == mId
        }

        return SerieList[0]
    }

    fun getSeries(): List<Serie> {
        return SerieList
    }

    fun getFirst(): Serie {
        return getSeries()[0]
    }

    fun addSerie(serie: Serie) {
        SerieList.add(serie)
    }

    fun deleteSerieByPosition(position: Int) {
        if (position >= 0 && position < SerieList.size) {
            SerieList.removeAt(position)
        }
    }
}
