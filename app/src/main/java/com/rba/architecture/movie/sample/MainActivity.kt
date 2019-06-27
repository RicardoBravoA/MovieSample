package com.rba.architecture.movie.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rba.architecture.movie.data.db.movie.MovieDataStoreFactory
import com.rba.architecture.movie.data.repository.MovieDataRepository
import com.rba.architecture.movie.domain.usecase.MovieUseCase
import com.rba.architecture.movie.sample.MainViewModel.UiViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val movieDataRepository = MovieDataRepository(MovieDataStoreFactory(this))
        mainViewModel = MainViewModel(MovieUseCase(movieDataRepository))

        mainAdapter = MainAdapter(mainViewModel::onClickMovie)
        rvData.adapter = mainAdapter
        mainViewModel.model.observe(this, Observer(::updateUi))

    }

    private fun updateUi(model: UiViewModel) {

        when (model) {
            is UiViewModel.Content -> {
                mainAdapter.list = model.movieModel.resultResponse!!
            }

            is UiViewModel.Navigation -> Log.i("z- log", model.movie.toString())
            UiViewModel.Refresh -> mainViewModel.onRefresh()
        }
    }

}
