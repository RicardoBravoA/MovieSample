package com.rba.architecture.movie.domain.model

data class ErrorModel(
    var statusCode: Int = 0,
    var statusMessage: String? = null,
    var success: Boolean? = false
)