package com.kumar.test.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumar.test.domain.model.PhotoDomainModel
import com.kumar.test.domain.usecase.GetPicturesListUseCase
import com.kumar.test.presentation.util.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PictureViewModel(
    private val getPicturesListUseCase: GetPicturesListUseCase
) : ViewModel() {


    private val _photoDisplayList = MutableLiveData<Event<List<PhotoDomainModel>>>()
    val photoDisplayList: LiveData<Event<List<PhotoDomainModel>>> get() = _photoDisplayList

    private val _onError = MutableLiveData<Event<Boolean>>()
    val onError: LiveData<Event<Boolean>> get() = _onError

    private val _noElement = MutableLiveData<Event<Boolean>>()
    val noElement: LiveData<Event<Boolean>> get() = _noElement

    private val errorHandler = CoroutineExceptionHandler { _, _ ->
        _onError.postValue(Event(true))
    }

    fun init() {
        fetchPictureList()
    }

    fun onRetryClick() {
        fetchPictureList()
    }

    private fun fetchPictureList() {
        viewModelScope.launch(errorHandler) {
            val list = getPicturesListUseCase.execute()
            if (list.isEmpty()) {
                _noElement.postValue(Event(true))
            } else {
                _photoDisplayList.postValue(Event(list))
            }
        }
    }
}

