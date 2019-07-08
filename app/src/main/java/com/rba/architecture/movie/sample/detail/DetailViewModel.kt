package com.rba.architecture.movie.sample.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {

    var data = MutableLiveData<String>()

    fun updateData(text: String) {
        Log.i("z- updateData", "true")
        data.value = text
    }

}