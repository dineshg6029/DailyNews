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
        private const val BASE_URL ="https://newsapi.org/v2/"
        private var newsApiService:NewsApiService ?= null;
        private var httpLoggingInterceptor:HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private var okHttpClient = OkHttpClient.Builder()
                                               .addInterceptor(httpLoggingInterceptor)
                                               .build()

        operator fun invoke() : NewsApiService?{
            if(newsApiService == null){
                synchronized(this){
                    if(newsApiService == null){
                        val retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        newsApiService = retrofit.create(NewsApiService::class.java)
                    }
                }
            }
            return newsApiService
        }
    }
}