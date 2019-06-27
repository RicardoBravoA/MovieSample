package com.rba.architecture.movie.data.db.movie

import com.rba.architecture.movie.data.api.ApiManager
import com.rba.architecture.movie.data.db.dao.MovieDao
import com.rba.architecture.movie.data.db.model.MovieEntity
import com.rba.architecture.movie.data.entity.response.MovieResponse
import com.rba.architecture.movie.data.mapper.ErrorMapper
import com.rba.architecture.movie.data.mapper.MovieMapper
import com.rba.architecture.movie.data.util.ErrorUtil
import com.rba.architecture.movie.domain.callback.MovieCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieServiceDataStore(private val movieDao: MovieDao) : MovieDataStore {

    private val movieMapper: MovieMapper = MovieMapper()
    private val errorMapper: ErrorMapper = ErrorMapper()

    override suspend fun getMovieList(movieCallback: MovieCallback<MovieModel, ErrorModel>) {
        val call = ApiManager.apiManager().movieList()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    userResponse.let {
                        val movieEntityList: List<MovieEntity> = movieMapper.transformWsForDbList(userResponse!!)
                        movieEntityList.map {
                            GlobalScope.launch {
                                movieDao.insertMovie(it)
                            }
                        }
                        movieCallback.onSuccess(movieMapper.transformResponseToModelList(userResponse))
                    }

                } else {
                    val error = ErrorUtil.parseError(response)!!
                    movieCallback.onError(errorMapper.transform(error))
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                movieCallback.onFailure(t)
            }

        })
    }

}