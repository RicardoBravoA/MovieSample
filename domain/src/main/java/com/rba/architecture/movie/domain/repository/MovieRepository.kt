package com.rba.architecture.movie.domain.repository

import com.rba.architecture.movie.domain.callback.BaseCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel

interface MovieRepository {

    suspend fun getMovieList(baseCallback: BaseCallback<MovieModel, ErrorModel>)

}