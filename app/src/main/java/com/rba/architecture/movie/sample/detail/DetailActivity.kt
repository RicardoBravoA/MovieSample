package com.rba.architecture.movie.sample.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rba.architecture.movie.sample.R

import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
    }

}
