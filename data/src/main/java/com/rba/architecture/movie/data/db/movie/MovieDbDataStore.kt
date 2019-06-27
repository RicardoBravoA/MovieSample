package com.rba.architecture.movie.data.db.movie

import com.rba.architecture.movie.data.db.dao.MovieDao
import com.rba.architecture.movie.data.db.model.MovieEntity
import com.rba.architecture.movie.data.mapper.MovieMapper
import com.rba.architecture.movie.domain.callback.MovieCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel

class MovieDbDataStore(private val movieDao: MovieDao) : MovieDataStore {

    private val movieMapper: MovieMapper = MovieMapper()

    override suspend fun getMovieList(movieCallback: MovieCallback<MovieModel, ErrorModel>) {
        val movieEntityList: List<MovieEntity> = movieDao.getMovieList()
        movieCallback.onSuccess(movieMapper.transformDbForWsList(movieEntityList))
    }

}