package com.rba.architecture.movie.domain.repository

import com.rba.architecture.movie.domain.callback.MovieCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel

interface MovieRepository {

    suspend fun getMovieList(api: String, movieCallback: MovieCallback<MovieModel, ErrorModel>)

}