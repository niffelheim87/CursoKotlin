package com.ompava.doglist_retrofit2

import com.google.gson.annotations.SerializedName

data class DogResponse(
    // nombre serializado igual al del campo del json del API
    @SerializedName("status") var status:String,
    @SerializedName("message") var images:List<String>
    )