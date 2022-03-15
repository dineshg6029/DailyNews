package com.demo.dailynews.ui.news.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.demo.dailynews.BuildConfig
import com.demo.dailynews.R
import com.demo.dailynews.data.model.Article
import com.demo.dailynews.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val app: Application
) : AndroidViewModel(app) {

    private val _articleList = MutableLiveData<List<Article>>()

    val articleList: LiveData<List<Article>>
        get() = _articleList

    val loadingStatus = MutableLiveData<Boolean>()

    val errorMessage = MutableLiveData<String>()

    fun getNewsArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            loadingStatus.postValue(true)
            newsRepository.getNews(BuildConfig.NEWS_KEY).collect {
                it.let {
                    if (it.status == app.getString(R.string.fetch_success_status)) {
                        _articleList.postValue(it.articles)
                    } else {
                        errorMessage.postValue(app.getString(R.string.fetch_failed_message))
                    }
                    loadingStatus.postValue(false)
                }
            }
        }
    }
}