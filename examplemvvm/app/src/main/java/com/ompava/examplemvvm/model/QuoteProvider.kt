package com.ompava.examplemvvm.model

class QuoteProvider {

    companion object {
        fun random(): QuoteModel {
            val position = (0..10).random()
            return quote[position]
        }

        private val quote = listOf(
            QuoteModel(
                quote = "hola1", author = "dfgyyyyyyyyyyfd"

            ), QuoteModel(
                quote = "hola2", author = "dfgbbbbbbbbbbbbfd"

            ), QuoteModel(
                quote = "hola3", author = "dfgfhfghgffd"

            ), QuoteModel(
                quote = "hola4", author = "dfdfg"

            ), QuoteModel(
                quote = "hola5", author = "dfgdfgd"

            ), QuoteModel(
                quote = "hola6", author = "dfgdfgd"

            ), QuoteModel(
                quote = "hola7", author = "dfgdfgd"

            ), QuoteModel(
                quote = "hola8", author = "dfgdfgd"

            ), QuoteModel(
                quote = "hola9", author = "dfgdfgd"

            ), QuoteModel(
                quote = "hola10", author = "dfgdfgd"

            )

        )
    }
}