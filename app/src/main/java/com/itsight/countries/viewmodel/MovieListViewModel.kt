package com.itsight.countries.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.itsight.countries.model.Movie
import com.itsight.countries.model.MoviesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MovieListViewModel: ViewModel() {

    private val moviesService = MoviesService()
    private val disposable = CompositeDisposable()

    val movies = MutableLiveData<Movie>()
    val moviesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchMovies()
    }

    private fun fetchMovies() {
        loading.value = true
        disposable.add(
            moviesService.getMovie()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Movie>() {
                    override fun onSuccess(value: Movie?) {
                        movies.value = value
                        moviesLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        moviesLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
