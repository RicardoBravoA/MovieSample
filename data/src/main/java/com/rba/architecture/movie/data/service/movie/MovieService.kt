package com.rba.architecture.movie.data.service.movie

import com.rba.architecture.movie.domain.callback.MovieCallback

interface MovieService<T, R> {

    fun getMovieList(api: String, movieCallback: MovieCallback<T, R>)

}