package com.ompava.examplemvvm.data

import com.ompava.examplemvvm.data.model.QuoteModel
import com.ompava.examplemvvm.data.model.QuoteProvider
import com.ompava.examplemvvm.data.network.QuoteService

class QuoteRepository {

    private val api = QuoteService()

    suspend fun getAllQuotes():List<QuoteModel>{
        val response = api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}