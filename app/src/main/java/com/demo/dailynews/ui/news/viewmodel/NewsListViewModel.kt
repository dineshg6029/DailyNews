package com.demo.dailynews.ui.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.dailynews.common.wrapEspressoIdlingResource
import com.demo.dailynews.data.NO_INTERNET_CONNECTION
import com.demo.dailynews.data.Resource
import com.demo.dailynews.data.model.NewsApiResult
import com.demo.dailynews.data.repository.NewsRepository
import com.java.predictweather.data.retrofit.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val networkUtils: NetworkUtils) : ViewModel() {

    private val _articleResourceResult = MutableLiveData<Resource<NewsApiResult>>()

    val articleResourceResult : LiveData<Resource<NewsApiResult>>
        get() = _articleResourceResult


    fun getNewsArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            _articleResourceResult.postValue(Resource.Loading())
            wrapEspressoIdlingResource {
                if(networkUtils.isConnected()){
                    newsRepository.getNews().collect {
                        _articleResourceResult.postValue(it)
                    }
                }else{
                    _articleResourceResult.postValue(Resource.DataError(NO_INTERNET_CONNECTION))
                }
            }
        }
    }
}