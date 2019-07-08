package com.rba.architecture.movie.sample.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rba.architecture.movie.sample.R
import com.rba.architecture.movie.sample.main.MainViewModel.UiViewModel
import com.rba.architecture.movie.sample.util.snackbar

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private var mainAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        srlData.setOnRefreshListener {
            mainViewModel.refresh()
        }

        mainViewModel = ViewModelProviders.of(this, MainViewModelFactory(application)).get(MainViewModel::class.java)

        initRecyclerView()
    }

    private fun initRecyclerView() {

        if (mainAdapter == null) {
            mainAdapter = MainAdapter(mainViewModel::onClickMovie)
            rvData.adapter = mainAdapter
        }
        mainViewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: UiViewModel) {

        when (model) {
            is UiViewModel.ShowData -> {
                mainAdapter?.list = model.movieModel.resultResponse!!

            }

            is UiViewModel.Loading -> {
                srlData.isRefreshing = model.show
            }

            is UiViewModel.Navigation -> Log.i("z- click", model.movie.toString())

            is UiViewModel.ShowError -> clData.snackbar(model.errorModel.statusMessage!!)

            is UiViewModel.ShowFailure -> clData.snackbar(model.defaultErrorModel.message)

            is UiViewModel.Refresh -> {
                mainViewModel.loadData()
            }
        }
    }

}
