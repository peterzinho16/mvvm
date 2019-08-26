package com.itsight.countries.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.itsight.countries.R
import com.itsight.countries.viewmodel.ListViewModel
import com.itsight.countries.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())
    lateinit var movieViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countryList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observerViewModel()

        movieViewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        movieViewModel.refresh()

        movieViewModel.movies.observe(this, Observer { movie ->
            movie?.let{
                txtMovieName.text = it.movieTitle
            }
        })

    }

    private fun observerViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                countriesAdapter.updateCountries(it)
                countryList.visibility = View.VISIBLE
            }
        })

        viewModel.countryLoadError.observe(this, Observer { err ->
            err?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { loading ->
            loading?.let {
                loading_view.visibility = if (loading) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    countryList.visibility = View.GONE
                }

            }
        })
    }
}
