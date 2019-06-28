package com.rba.architecture.movie.domain.model

data class DefaultErrorModel(
    var message: String
) {
    constructor() : this("Ocurrió un error...")
}