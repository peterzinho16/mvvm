package com.itsight.countries.model

import com.itsight.countries.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [CountriesService::class])
interface CountriesComponent {
    fun inject(listViewModel: ListViewModel)
}
