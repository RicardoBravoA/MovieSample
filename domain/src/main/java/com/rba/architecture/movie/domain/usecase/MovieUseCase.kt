package com.rba.architecture.movie.domain.usecase

import com.rba.architecture.movie.domain.callback.MovieCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel
import com.rba.architecture.movie.domain.repository.MovieRepository

class MovieUseCase(private val movieRepository: MovieRepository) {

    suspend fun getMovieList(movieCallback: MovieCallback<MovieModel, ErrorModel>) {
        movieRepository.getMovieList(movieCallback)
    }

}