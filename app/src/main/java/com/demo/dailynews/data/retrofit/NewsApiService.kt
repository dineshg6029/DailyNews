package com.demo.dailynews.data.retrofit

import com.demo.dailynews.data.model.NewsApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines?sources=bbc-news")
    suspend fun getHeadlines(@Query("apiKey") apiKey:String): Response<NewsApiResult>

    companion object{
        const val BASE_URL ="https://newsapi.org/v2/"
    }
}