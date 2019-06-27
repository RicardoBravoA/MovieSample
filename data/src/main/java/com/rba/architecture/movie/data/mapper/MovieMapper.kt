package com.rba.architecture.movie.data.mapper

import com.rba.architecture.movie.data.db.model.MovieEntity
import com.rba.architecture.movie.data.entity.response.MovieResponse
import com.rba.architecture.movie.domain.model.MovieModel

class MovieMapper {

    private fun transform(movieResponse: MovieResponse.ResultResponse): MovieModel.ResultResponse {

        val movieModel: MovieModel.ResultResponse = MovieModel.ResultResponse()

        movieModel.id = movieResponse.id
        movieModel.voteCount = movieResponse.voteCount
        movieModel.isVideo = movieResponse.isVideo
        movieModel.voteAverage = movieResponse.voteAverage
        movieModel.title = movieResponse.title
        movieModel.popularity = movieResponse.popularity
        movieModel.posterPath = movieResponse.posterPath
        movieModel.originalLanguage = movieResponse.originalLanguage
        movieModel.originalTitle = movieResponse.originalTitle
        movieModel.backdropPath = movieResponse.backdropPath
        movieModel.isAdult = movieResponse.isAdult
        movieModel.overview = movieResponse.overview
        movieModel.releaseDate = movieResponse.releaseDate

        return movieModel
    }

    private fun transformForDb(movieResponse: MovieResponse.ResultResponse): MovieEntity {

        val movieEntity = MovieEntity()
        movieEntity.id = movieResponse.id
        movieEntity.voteCount = movieResponse.voteCount
        movieEntity.isVideo = movieResponse.isVideo
        movieEntity.voteAverage = movieResponse.voteAverage
        movieEntity.title = movieResponse.title
        movieEntity.popularity = movieResponse.popularity
        movieEntity.posterPath = movieResponse.posterPath
        movieEntity.originalLanguage = movieResponse.originalLanguage
        movieEntity.originalTitle = movieResponse.originalTitle
        movieEntity.backdropPath = movieResponse.backdropPath
        movieEntity.isAdult = movieResponse.isAdult
        movieEntity.overview = movieResponse.overview
        movieEntity.releaseDate = movieResponse.releaseDate

        return movieEntity
    }

    private fun transformDbForWs(movieEntity: MovieEntity): MovieModel.ResultResponse {

        val movieModel = MovieModel.ResultResponse()
        movieModel.id = movieEntity.id
        movieModel.voteCount = movieEntity.voteCount
        movieModel.isVideo = movieEntity.isVideo
        movieModel.voteAverage = movieEntity.voteAverage
        movieModel.title = movieEntity.title
        movieModel.popularity = movieEntity.popularity
        movieModel.posterPath = movieEntity.posterPath
        movieModel.originalLanguage = movieEntity.originalLanguage
        movieModel.originalTitle = movieEntity.originalTitle
        movieModel.backdropPath = movieEntity.backdropPath
        movieModel.isAdult = movieEntity.isAdult
        movieModel.overview = movieEntity.overview
        movieModel.releaseDate = movieEntity.releaseDate

        return movieModel
    }

    fun transformResponseToModelList(movieResponse: MovieResponse): MovieModel {

        val movieModel = MovieModel()
        movieModel.totalResults = movieResponse.totalResults
        movieModel.totalPages = movieResponse.totalPages
        movieModel.page = movieResponse.page

        val resultResponse = arrayListOf<MovieModel.ResultResponse>()

        movieResponse.resultResponse.let {
            movieResponse.resultResponse!!.map {
                resultResponse.add(transform(it))
            }
        }

        movieModel.resultResponse = resultResponse

        return movieModel

    }

    fun transformWsForDbList(movieResponse: MovieResponse): List<MovieEntity> {
        val movieEntityList = arrayListOf<MovieEntity>()
        movieResponse.resultResponse.let {
            movieResponse.resultResponse!!.map {
                movieEntityList.add(transformForDb(it))
            }
        }
        return movieEntityList
    }

    fun transformDbForWsList(list: List<MovieEntity>): MovieModel {
        val movieModel = MovieModel()
        val resultResponse = mutableListOf<MovieModel.ResultResponse>()
        list.map { resultResponse.add(transformDbForWs(it)) }
        movieModel.resultResponse = resultResponse
        return movieModel
    }

}

