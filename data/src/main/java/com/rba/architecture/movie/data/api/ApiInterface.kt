package com.rba.architecture.movie.data.api

import com.rba.architecture.movie.data.entity.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    fun movieList(@Query("api_key") key: String, @Query("language") language: String = "en-US"): Call<MovieResponse>

}