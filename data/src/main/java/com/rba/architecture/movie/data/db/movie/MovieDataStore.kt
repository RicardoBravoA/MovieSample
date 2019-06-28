package com.rba.architecture.movie.data.db.movie

import com.rba.architecture.movie.domain.callback.BaseCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel

interface MovieDataStore {

    suspend fun getMovieList(baseCallback: BaseCallback<MovieModel, ErrorModel>)

}