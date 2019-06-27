package com.rba.architecture.movie.sample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rba.architecture.movie.domain.callback.MovieCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel
import com.rba.architecture.movie.domain.usecase.MovieUseCase
import com.rba.architecture.movie.sample.util.ScopedViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val movieUseCase: MovieUseCase) : ScopedViewModel() {

    private val _model = MutableLiveData<UiViewModel>()
    val model: LiveData<UiViewModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    init {
        initScope()
    }

    private fun refresh() {
        _model.value = UiViewModel.Refresh
    }

    fun onRefresh() {
        launch {
            movieUseCase.getMovieList(object : MovieCallback<MovieModel, ErrorModel> {
                    override fun onSuccess(t: MovieModel) {
                        _model.value = UiViewModel.Content(t)
                        Log.i("z- inSuccess", t.toString())
                    }

                    override fun onError(r: ErrorModel) {
                        Log.i("z- onError", r.toString())
                    }

                    override fun onFailure(t: Throwable) {
                        Log.i("z- onFailure", t.toString())
                    }
                }
            )

        }
    }

    fun onClickMovie(movie: MovieModel.ResultResponse) {
        _model.value = UiViewModel.Navigation(movie)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

    sealed class UiViewModel {
        class Content(val movieModel: MovieModel) : UiViewModel()
        class Navigation(val movie: MovieModel.ResultResponse) : UiViewModel()
        object Refresh : UiViewModel()
    }

}