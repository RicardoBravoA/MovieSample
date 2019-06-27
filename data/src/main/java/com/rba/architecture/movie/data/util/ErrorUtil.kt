package com.rba.architecture.movie.data.util

import com.rba.architecture.movie.data.api.ApiManager
import com.rba.architecture.movie.data.entity.response.ErrorResponse
import retrofit2.Response
import java.io.IOException

object ErrorUtil {
    fun parseError(response: Response<*>): ErrorResponse? {

        val converter = ApiManager.retrofit.responseBodyConverter<ErrorResponse>(
            ErrorResponse::class.java,
            arrayOfNulls<Annotation>(0)
        )

        val error: ErrorResponse

        try {
            error = converter.convert(response.errorBody()!!)!!
        } catch (e: IOException) {
            return ErrorResponse()
        }

        return error
    }
}