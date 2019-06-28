package com.rba.architecture.movie.domain.callback

interface BaseCallback<T, R> {

    fun onSuccess(t: T)

    fun onError(r: R)

    fun onFailure(t: Throwable)

}