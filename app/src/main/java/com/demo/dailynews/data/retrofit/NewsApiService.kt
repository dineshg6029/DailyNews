package com.demo.dailynews.data.retrofit

import com.demo.dailynews.data.model.NewsApiResult
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines?sources=bbc-news")
    suspend fun getHeadlines(@Query("apiKey") apiKey:String): NewsApiResult

    companion object{
        const val BASE_URL ="https://newsapi.org/v2/"
    }
}