package com.demo.dailynews.data.repository

import com.demo.dailynews.data.model.NewsApiResult
import com.demo.dailynews.data.retrofit.NewsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApiService: NewsApiService) {

    suspend fun getNews(apiKey:String) : Flow<NewsApiResult>{
        return flow {
            val result = newsApiService.getHeadlines(apiKey)
            emit(result)
        }
    }

}