package com.itsight.countries.model

import com.itsight.countries.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class MoviesService {

    @Inject
    lateinit var api:MoviesApi
    init {
        DaggerApiComponent.create().injectMovie(this)
    }

    fun getMovie(): Single<Movie> = api.getMovie("tt1285016", "50ab9a3d")
}
