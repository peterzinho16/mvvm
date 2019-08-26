package com.itsight.countries.di

import com.itsight.countries.model.CountriesApi
import com.itsight.countries.model.MoviesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL_COUNTRIES_API = "https://raw.githubusercontent.com"
    private val BASE_URL_MOVIES_API = "https://www.omdbapi.com/"

    @Provides
    fun provideCountriesApi(): CountriesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_COUNTRIES_API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    fun provideMoviesApi(): MoviesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIES_API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }
}
