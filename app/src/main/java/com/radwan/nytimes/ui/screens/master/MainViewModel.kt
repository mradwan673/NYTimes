package com.radwan.nytimes.ui.screens.master

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radwan.nytimes.data.model.NetworkResult
import com.radwan.nytimes.repository.MainRepository
import com.radwan.nytimes.data.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _articleResponse = MutableLiveData<NetworkResult<List<Article>>>()
    val articleResponse: LiveData<NetworkResult<List<Article>>> = _articleResponse

    init {
        getArticlesFlow()
    }

     fun getArticlesFlow() {
        viewModelScope.launch {
            mainRepository.getArticlesResponseFlow().collect {
                _articleResponse.postValue(it)
            }
        }
    }
}

