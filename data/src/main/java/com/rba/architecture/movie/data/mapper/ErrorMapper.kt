package com.rba.architecture.movie.data.mapper

import com.rba.architecture.movie.data.entity.response.ErrorResponse
import com.rba.architecture.movie.domain.model.ErrorModel

class ErrorMapper {

    fun transform(errorResponse: ErrorResponse): ErrorModel {

        val errorModel = ErrorModel()
        errorModel.statusCode = errorResponse.statusCode
        errorModel.statusMessage = errorResponse.statusMessage
        errorModel.success = errorResponse.success

        return errorModel
    }

}