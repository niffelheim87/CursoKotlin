package com.ompava.masterdetail.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ompava.masterdetail.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

class SuperHeroProvider(context: Context) {
    val superHeroList: MutableList<SuperHero> = mutableListOf()

    init {
        val raw = context.resources.openRawResource(R.raw.superhero_json)
        val rd = BufferedReader(InputStreamReader(raw))
        val listType: Type = object : TypeToken<MutableList<SuperHero?>?>() {}.type
        val gson = Gson()
        superHeroList.addAll(gson.fromJson(rd, listType))

    }


    fun getSuperHeroById(mId: Int?): SuperHero {
        val superhero = getSuperheros().filter { superHero ->
            superHero.id == mId
        }

        return superhero[0]
    }

    fun getSuperheros(): List<SuperHero> {
        return superHeroList
    }

    fun getFirstID(): Int {
        return getSuperheros()[0].id
    }

}