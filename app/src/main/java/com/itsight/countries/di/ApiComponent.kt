package com.itsight.countries.di

import com.itsight.countries.model.CountriesService
import com.itsight.countries.model.MoviesService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun injectCountries(service: CountriesService)
    fun injectMovie(service: MoviesService)
}
