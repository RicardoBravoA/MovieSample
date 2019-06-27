package com.rba.architecture.movie.sample.util

import androidx.lifecycle.ViewModel
import androidx.annotation.CallSuper

abstract class ScopedViewModel : ViewModel(), Scope by Scope.Impl() {

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}