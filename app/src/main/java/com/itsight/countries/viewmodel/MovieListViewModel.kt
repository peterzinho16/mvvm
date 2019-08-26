package com.itsight.countries.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.itsight.countries.model.Movie
import com.itsight.countries.model.MoviesService
import io.reactivex.disposables.CompositeDisposable

class CountryListViewModel: ViewModel() {

    private val moviesService = MoviesService()
    private val disposable = CompositeDisposable()

    val movies = MutableLiveData<List<Movie>>()
    val moviesLoadingError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
    }

    override fun onCleared() {
        super.onCleared()
    }
}
