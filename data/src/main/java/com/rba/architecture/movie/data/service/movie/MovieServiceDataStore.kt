package com.rba.architecture.movie.data.service.movie

import com.rba.architecture.movie.data.api.ApiManager
import com.rba.architecture.movie.data.db.dao.MovieDao
import com.rba.architecture.movie.data.db.entity.MovieEntity
import com.rba.architecture.movie.data.db.movie.MovieDataStore
import com.rba.architecture.movie.data.entity.response.MovieResponse
import com.rba.architecture.movie.data.mapper.ErrorMapper
import com.rba.architecture.movie.data.mapper.MovieMapper
import com.rba.architecture.movie.data.util.RetrofitErrorUtil
import com.rba.architecture.movie.domain.callback.BaseCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieServiceDataStore(private val movieDao: MovieDao) : MovieDataStore {

    override suspend fun getMovieList(baseCallback: BaseCallback<MovieModel, ErrorModel>) {
        val call = ApiManager.apiManager().movieList()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    movieResponse?.let {
                        saveInDb(movieResponse)
                        baseCallback.onSuccess(MovieMapper.transformResponseToModelList(movieResponse))
                    }

                } else {
                    val error = RetrofitErrorUtil.parseError(response)!!
                    baseCallback.onError(ErrorMapper.transform(error))
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                baseCallback.onFailure(t)
            }

        })
    }

    private fun saveInDb(movieResponse: MovieResponse) {
        val movieEntityList: List<MovieEntity> = MovieMapper.transformWsForDbList(movieResponse)
        movieEntityList.map {
            GlobalScope.launch {
                movieDao.insertMovie(it)
            }
        }
    }

}