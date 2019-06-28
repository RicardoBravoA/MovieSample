package com.rba.architecture.movie.data.db.movie

import com.rba.architecture.movie.data.db.dao.MovieDao
import com.rba.architecture.movie.data.db.entity.MovieEntity
import com.rba.architecture.movie.data.mapper.MovieMapper
import com.rba.architecture.movie.domain.callback.BaseCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel

class MovieDbDataStore(private val movieDao: MovieDao) : MovieDataStore {

    override suspend fun getMovieList(baseCallback: BaseCallback<MovieModel, ErrorModel>) {
        val movieEntityList: List<MovieEntity> = movieDao.getMovieList()
        baseCallback.onSuccess(MovieMapper.transformDbForWsList(movieEntityList))
    }

}