package com.demo.dailynews.data.retrofit

import com.demo.dailynews.BuildConfig
import com.demo.dailynews.data.model.Article
import com.demo.dailynews.data.model.NewsApiResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class NewsApiServiceTest {

    private val newsHeadline = "HeadLine"
    private val newsDescription = "Description"
    private val newsUrl = "newsUrl"
    private val newsImageUrl = "imageUrl"
    private val newsDateTime ="dateTime"

    private var article: Article = Article(
        newsHeadline,
        newsDescription,
        newsUrl,
        newsImageUrl,
        newsDateTime)
    var newsApiResult:NewsApiResult = NewsApiResult("ok",1, arrayListOf(article))

    private val newsApiService:NewsApiService = object : NewsApiService{
        override suspend fun getHeadlines(apiKey: String): Response<NewsApiResult> {
            return Response.success(newsApiResult)
        }
    }

    @Test
    fun testNewsApiService() {
        runBlockingTest {
            assertThat(newsApiService.getHeadlines(BuildConfig.NEWS_KEY).isSuccessful, `is`(true))
        }
    }
}