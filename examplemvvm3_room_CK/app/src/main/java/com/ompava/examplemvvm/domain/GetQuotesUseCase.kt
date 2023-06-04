package com.ompava.examplemvvm.domain

import com.ompava.examplemvvm.data.QuoteRepository
import com.ompava.examplemvvm.data.model.QuoteModel

class GetQuotesUseCase {

    private val repository = QuoteRepository()

    suspend operator fun invoke():List<QuoteModel>? = repository.getAllQuotes()

}