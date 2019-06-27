package com.rba.architecture.movie.data.db.movie

import com.rba.architecture.movie.domain.callback.MovieCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel

interface MovieDataStore {

    suspend fun getMovieList(api: String, movieCallback: MovieCallback<MovieModel, ErrorModel>)

}