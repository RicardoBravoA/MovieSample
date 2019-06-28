package com.rba.architecture.movie.domain.usecase

import com.rba.architecture.movie.domain.callback.BaseCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel
import com.rba.architecture.movie.domain.repository.MovieRepository

class MovieUseCase(private val movieRepository: MovieRepository) {

    suspend fun getMovieList(baseCallback: BaseCallback<MovieModel, ErrorModel>) {
        movieRepository.getMovieList(baseCallback)
    }

}