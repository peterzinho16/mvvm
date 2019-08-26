package com.itsight.countries.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("/")
    fun getMovie(@Query("i") tt: String, @Query("apikey") apiKey: String):Single<Movie>
}
