package com.ompava.examplemvvm.domain

import com.ompava.examplemvvm.data.QuoteRepository
import com.ompava.examplemvvm.data.model.QuoteModel
import com.ompava.examplemvvm.data.model.QuoteProvider

class GetRandomQuoteUseCase {

    private val repository = QuoteRepository()

    operator fun invoke():QuoteModel? {
        val quotes:List<QuoteModel> = QuoteProvider.quotes
        if (!quotes.isNullOrEmpty()){
            val randomNumber = (quotes.indices).random()
            return quotes[3]
        }
        return null

    }

}