package com.rba.architecture.movie.sample.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rba.architecture.movie.data.util.ErrorUtil
import com.rba.architecture.movie.domain.callback.BaseCallback
import com.rba.architecture.movie.domain.model.DefaultErrorModel
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel
import com.rba.architecture.movie.domain.usecase.MovieUseCase
import com.rba.architecture.movie.sample.util.ScopedViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val movieUseCase: MovieUseCase) : ScopedViewModel() {

    private val mutableModel = MutableLiveData<UiViewModel>()
    val model: LiveData<UiViewModel>
        get() {
            if (mutableModel.value == null) refresh()
            return mutableModel
        }

    init {
        initScope()
    }

    fun refresh() {
        mutableModel.value = UiViewModel.Refresh
    }

    fun loadData() {
        launch {
            mutableModel.value = UiViewModel.Loading(true)
            movieUseCase.getMovieList(object : BaseCallback<MovieModel, ErrorModel> {
                override fun onSuccess(t: MovieModel) {
                    mutableModel.value = UiViewModel.ShowData(t)
                    mutableModel.value = UiViewModel.Loading(false)
                }

                override fun onError(r: ErrorModel) {
                    mutableModel.value = UiViewModel.ShowError(r)
                    mutableModel.value = UiViewModel.Loading(false)
                }

                override fun onFailure(t: Throwable) {
                    mutableModel.value = UiViewModel.ShowFailure(
                        ErrorUtil.errorHandler(t)
                    )
                    mutableModel.value = UiViewModel.Loading(false)
                }
            }

            )
        }
    }

    fun onClickMovie(movie: MovieModel.ResultResponse) {
        mutableModel.value = UiViewModel.Navigation(movie)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

    sealed class UiViewModel {
        class Loading(val show: Boolean) : UiViewModel()
        class ShowData(val movieModel: MovieModel) : UiViewModel()
        class ShowError(val errorModel: ErrorModel) : UiViewModel()
        class ShowFailure(val defaultErrorModel: DefaultErrorModel) : UiViewModel()
        class Navigation(val movie: MovieModel.ResultResponse) : UiViewModel()
        object Refresh : UiViewModel()
    }

}