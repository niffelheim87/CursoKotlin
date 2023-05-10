package com.ompava.seriesrecyclergraph

import com.ompava.seriesrecyclergraph.model.Serie

interface OnItemClick {
    fun onItemClick(serie: Serie, position: Int)

}