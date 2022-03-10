package com.kumar.test.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kumar.test.presentation.util.Event
import kotlinx.coroutines.CoroutineExceptionHandler

class PictureViewModel : ViewModel() {


    private val _onError = MutableLiveData<Event<Boolean>>()
     val onError : LiveData<Event<Boolean>> get() = _onError

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }


    private fun onError(throwable: Throwable? = null) {
        _onError.postValue(Event(true))
    }


}

