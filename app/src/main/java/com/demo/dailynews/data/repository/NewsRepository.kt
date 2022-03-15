package com.demo.dailynews.data.repository

import com.demo.dailynews.data.model.NewsApiResult
import com.demo.dailynews.data.retrofit.NewsApiService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityRetainedScoped
class NewsRepository @Inject constructor(private val newsApiService: NewsApiService) {

    suspend fun getNews(apiKey:String) : Flow<NewsApiResult>{
        return flow {
            val result = newsApiService.getHeadlines(apiKey)
            emit(result)
        }
    }

}