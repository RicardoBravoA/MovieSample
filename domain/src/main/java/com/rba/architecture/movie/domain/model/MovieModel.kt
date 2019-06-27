package com.rba.architecture.movie.domain.model

data class MovieModel(

    var page: Int = 0,
    var totalResults: Int = 0,
    var totalPages: Int = 0,
    var resultResponse: List<ResultResponse>? = arrayListOf()
) {
    data class ResultResponse(
        var id: Int = 0,
        var voteCount: Int = 0,
        var isVideo: Boolean = false,
        var voteAverage: Double = 0.toDouble(),
        var title: String? = null,
        var popularity: Double = 0.toDouble(),
        var posterPath: String? = null,
        var originalLanguage: String? = null,
        var originalTitle: String? = null,
        var backdropPath: String? = null,
        var isAdult: Boolean = false,
        var overview: String? = null,
        var releaseDate: String? = null
    )
}