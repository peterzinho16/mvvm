package com.itsight.countries.model

import com.itsight.countries.di.DaggerApiComponent
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import javax.inject.Inject

@Module
class CountriesService: CountriesBaseService {

    @Inject
    lateinit var api: CountriesApi

    init {
        DaggerApiComponent.create().injectCountries(this)
    }

    @Provides
    fun getCountryService(): CountriesBaseService{
        return CountriesService()
    }

    override fun getCountries(): Single<List<Country>> = api.getCountries()

}

interface CountriesBaseService{
    fun getCountries(): Single<List<Country>>
}
