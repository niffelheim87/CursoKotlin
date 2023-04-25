package com.ompava.myrecycler.models

data class Serie(
    val id: Int,
    val name: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val premiered: String,
    val officialSite: String,
    val rating: Double,
    val image: String,
    val summary: String
) {


}
