package com.rba.architecture.movie.data.repository

import com.rba.architecture.movie.data.db.movie.MovieDataStoreFactory
import com.rba.architecture.movie.domain.callback.MovieCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel
import com.rba.architecture.movie.domain.repository.MovieRepository

class MovieDataRepository(private val movieDataStoreFactory: MovieDataStoreFactory) : MovieRepository {

    override suspend fun getMovieList(api: String, movieCallback: MovieCallback<MovieModel, ErrorModel>) {
        val movieDataStore = movieDataStoreFactory.create()
        movieDataStore.getMovieList(api, movieCallback)
    }

}