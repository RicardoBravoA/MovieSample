package com.rba.architecture.movie.data.entity.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    var page: Int = 0,
    @SerializedName("total_results")
    var totalResults: Int = 0,
    @SerializedName("total_pages")
    var totalPages: Int = 0,
    @SerializedName("results")
    var resultResponse: List<ResultResponse>? = arrayListOf()
) {
    data class ResultResponse(
        var id: Int = 0,
        @SerializedName("vote_count")
        var voteCount: Int = 0,
        var isVideo: Boolean = false,
        @SerializedName("vote_average")
        var voteAverage: Double = 0.toDouble(),
        var title: String? = null,
        var popularity: Double = 0.toDouble(),
        @SerializedName("poster_path")
        var posterPath: String? = null,
        @SerializedName("original_language")
        var originalLanguage: String? = null,
        @SerializedName("original_title")
        var originalTitle: String? = null,
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,
        var isAdult: Boolean = false,
        var overview: String? = null,
        @SerializedName("release_date")
        var releaseDate: String? = null
    )
}