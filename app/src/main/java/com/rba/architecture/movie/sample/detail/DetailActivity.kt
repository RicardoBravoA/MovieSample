package com.rba.architecture.movie.sample.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rba.architecture.movie.sample.R

import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        val detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        Log.i("z- detail", detailViewModel.toString())

        detailViewModel.updateData("Hola")

        detailViewModel.data.observe(this, Observer {
            Log.i("z- observer", "true")
        })
    }
}
