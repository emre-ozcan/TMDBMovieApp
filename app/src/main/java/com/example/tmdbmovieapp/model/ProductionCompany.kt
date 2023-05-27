package com.example.tmdbmovieapp.model


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("name")
    val name: String?,
)