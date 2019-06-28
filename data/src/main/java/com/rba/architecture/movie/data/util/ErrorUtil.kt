package com.rba.architecture.movie.data.util

import com.rba.architecture.movie.domain.model.DefaultErrorModel

object ErrorUtil {

    fun errorHandler(error: Throwable): DefaultErrorModel {

        val errorException: RetrofitException =
            if (error is RetrofitException) {
                error
            } else {
                RetrofitException.retrofitException(error)
            }

        return when (errorException.kind) {
            RetrofitException.Kind.HTTP -> errorException.getErrorBodyAs(DefaultErrorModel::class.java)!!
            RetrofitException.Kind.NETWORK -> DefaultErrorModel()
            else -> DefaultErrorModel()
        }

    }
}