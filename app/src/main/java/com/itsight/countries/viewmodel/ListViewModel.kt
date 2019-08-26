package com.itsight.countries.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.itsight.countries.model.CountriesBaseService
import com.itsight.countries.model.CountriesService
import com.itsight.countries.model.Country
import com.itsight.countries.model.DaggerCountriesComponent
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel: ViewModel(){

    @Inject
    lateinit var countriesService: CountriesBaseService
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init{
        DaggerCountriesComponent.create().inject(this)
    }

    fun refresh(){
        fetchCountries()
    }

    private fun fetchCountries(){
        loading.value = true
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(value: List<Country>?) {
                        countries.value = value
                        countryLoadError.value = false
                        loading.value = false
                    }
                    override fun onError(e: Throwable?) {
                        countryLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     *
     *
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
