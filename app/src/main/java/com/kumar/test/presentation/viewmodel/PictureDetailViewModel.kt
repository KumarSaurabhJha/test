package com.kumar.test.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumar.test.domain.model.PhotoDomainModel
import com.kumar.test.domain.usecase.GetPictureUseCase
import com.kumar.test.presentation.util.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PictureDetailViewModel(
    private val getPictureUseCase: GetPictureUseCase
) : ViewModel() {


    private val _photo = MutableLiveData<Event<PhotoDomainModel>>()
    val photo: LiveData<Event<PhotoDomainModel>> get() = _photo

    private val _onError = MutableLiveData<Event<Boolean>>()
    val onError: LiveData<Event<Boolean>> get() = _onError

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Kumi", "error", throwable)
        _onError.postValue(Event(true))
    }

    fun init(id: Int) {
        if (id == -1) {
            _onError.postValue(Event(true))
        } else {
            fetchPicture(id)
        }
    }

    private fun fetchPicture(id: Int) {
        viewModelScope.launch(errorHandler) {
            val photo = getPictureUseCase.execute(id)
            _photo.postValue(Event(photo))
        }
    }

}

