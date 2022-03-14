package com.demo.dailynews.data.repository

import com.demo.dailynews.data.model.NewsApiResult
import com.demo.dailynews.data.retrofit.NewsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository(val newsApiService: NewsApiService) {

    suspend fun getNews(apiKey:String) : Flow<NewsApiResult>{
        return flow {
            kotlinx.coroutines.delay(3000L)
            val result = newsApiService.getHeadlines(apiKey)
            emit(result)
        }
    }

}