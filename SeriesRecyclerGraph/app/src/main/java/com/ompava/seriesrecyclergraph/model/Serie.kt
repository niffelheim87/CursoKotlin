package com.ompava.seriesrecyclergraph.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ompava.seriesrecyclergraph.R
import java.io.BufferedReader
import java.io.InputStreamReader

data class Serie(
    val id: Int,
    val name: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val premiered: String,
    val officialSite: String?,
    val rating: Double,
    val image: String,
    val summary: String
) {
    companion object {

        private var serieList: MutableList<Serie> = mutableListOf()

        fun loadSeries(context: Context) {
            if (serieList.isNotEmpty()) {
                // Los datos ya se cargaron previamente
                return
            }

            val gson = Gson()

            // Leer archivo JSON desde la carpeta /res/raw
            val inputStream = context.resources.openRawResource(R.raw.datos_json)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val json = bufferedReader.use { it.readText() }

            // Convertir el JSON a una lista de objetos Serie utilizando la biblioteca Gson
            val listType = object : TypeToken<List<Serie>>() {}.type
            serieList = gson.fromJson(json, listType)
        }

        // Obtener la lista de series
        fun getSeries(context: Context): MutableList<Serie> {
            // Cargar las series si aún no se han cargado
            loadSeries(context)
            return serieList
        }

        // Obtener una serie por su ID
        fun getSerieByID(context: Context, id: Int): Serie? {
            // Obtener todas las series y buscar la que coincida con el ID especificado
            return getSeries(context).find { it.id == id }
        }

        // Obtener la primera serie de la lista
        fun getFirst(context: Context): Serie? {
            // Obtener la serie por su ID, en este caso, 0 representa la primera serie
            return getSerieByID(context, 0)
        }

        // Agregar una serie a la lista
        fun addSerie(serie: Serie) {
            // Agregar la serie a la lista existente
            serieList.add(serie)
        }

        // Eliminar una serie por posición
        fun deleteSerieByPosition(position: Int) {
            // Verificar que la posición esté dentro de los límites de la lista y eliminar la serie correspondiente
            if (position in 0 until serieList.size) {
                serieList.removeAt(position)
            }
        }

    }
}
