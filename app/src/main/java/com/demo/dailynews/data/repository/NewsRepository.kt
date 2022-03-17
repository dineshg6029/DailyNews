package com.demo.dailynews.data.repository

import com.demo.dailynews.BuildConfig
import com.demo.dailynews.data.NETWORK_ERROR
import com.demo.dailynews.data.Resource
import com.demo.dailynews.data.model.NewsApiResult
import com.demo.dailynews.data.retrofit.NewsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApiService: NewsApiService){

    suspend fun getNews() : Flow<Resource<NewsApiResult>>{
        return flow {
            try {
                val resultResponse = newsApiService.getHeadlines(BuildConfig.NEWS_KEY)
                val result = if (resultResponse.isSuccessful) {
                    Resource.Success(resultResponse.body() as NewsApiResult)
                } else {
                    Resource.DataError(errorCode = resultResponse.code())
                }
                emit(result)
            } catch (e: IOException) {
                emit(Resource.DataError(errorCode = NETWORK_ERROR))
            }
        }
    }

}