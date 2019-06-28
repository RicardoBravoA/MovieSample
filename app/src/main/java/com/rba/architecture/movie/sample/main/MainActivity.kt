package com.rba.architecture.movie.sample.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rba.architecture.movie.data.db.movie.MovieDataStoreFactory
import com.rba.architecture.movie.data.repository.MovieDataRepository
import com.rba.architecture.movie.domain.usecase.MovieUseCase
import com.rba.architecture.movie.sample.R
import com.rba.architecture.movie.sample.main.MainViewModel.UiViewModel
import com.rba.architecture.movie.sample.util.snackbar

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        srlData.setOnRefreshListener {
            mainViewModel.refresh()
        }

        val movieDataRepository = MovieDataRepository(MovieDataStoreFactory(this))
        mainViewModel = MainViewModel(MovieUseCase(movieDataRepository))

        mainAdapter = MainAdapter(mainViewModel::onClickMovie)
        rvData.adapter = mainAdapter
        mainViewModel.model.observe(this, Observer(::updateUi))

    }

    private fun updateUi(model: UiViewModel) {

        when (model) {
            is UiViewModel.ShowData -> {
                mainAdapter.list = model.movieModel.resultResponse!!

            }

            is UiViewModel.Loading -> {
                srlData.isRefreshing = model.show
            }

            is UiViewModel.Navigation -> Log.i("z- click", model.movie.toString())

            is UiViewModel.Refresh -> {
                mainViewModel.loadData()
            }

            is UiViewModel.ShowError -> clData.snackbar(model.errorModel.statusMessage!!)

            is UiViewModel.ShowFailure -> clData.snackbar(model.defaultErrorModel.message)
        }
    }

}
