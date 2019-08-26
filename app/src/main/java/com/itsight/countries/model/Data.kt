package com.itsight.countries.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val countryName: String?,
    @SerializedName("capital")
    val countryCapital: String?,
    @SerializedName("flagPNG")
    val flag: String?
)

data class Movie(

    @SerializedName("Title")
    val movieTitle: String,
    @SerializedName("Year")
    val movieYear: String,
    @SerializedName("Plot")
    val moviePlot: String
)
