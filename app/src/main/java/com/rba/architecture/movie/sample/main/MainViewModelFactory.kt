package com.rba.architecture.movie.sample.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rba.architecture.movie.data.db.movie.MovieDataStoreFactory
import com.rba.architecture.movie.data.repository.MovieDataRepository
import com.rba.architecture.movie.domain.usecase.MovieUseCase

class MainViewModelFactory constructor(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            val movieDataRepository = MovieDataRepository(MovieDataStoreFactory(application))
            val movieUseCase = MovieUseCase(movieDataRepository)
            MainViewModel(movieUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}