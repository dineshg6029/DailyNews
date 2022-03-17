package com.demo.dailynews.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.dailynews.BuildConfig
import com.demo.dailynews.data.model.Article
import com.demo.dailynews.data.model.NewsApiResult
import com.demo.dailynews.data.retrofit.NewsApiService
import com.demo.dailynews.utils.MainCoroutineRule
import com.util.InstantExecutorExtension
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Response

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class NewsRepositoryTest {

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val newsApiService:NewsApiService = mockk()
    private lateinit var newsRepository: NewsRepository
    private var newsApiResult: NewsApiResult

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

    init {
        newsApiResult = NewsApiResult("ok",1, arrayListOf(article))
    }

    @Test
    fun getNewsSuccess() {
        runBlocking {
            coEvery {newsApiService.getHeadlines(BuildConfig.NEWS_KEY)} returns Response.success(newsApiResult)
            newsRepository = NewsRepository(newsApiService)
            newsRepository.getNews()
            assertThat(newsApiService.getHeadlines(BuildConfig.NEWS_KEY).isSuccessful,`is`(true))
            newsRepository.getNews().collect {
                assertThat(it.data,`is`(newsApiResult))
            }
        }
    }
    @Test
    fun getNewsFailure() {
        runBlocking {
            val mockResponseBody:ResponseBody = mockk()
            val errorCode = 401
            every { mockResponseBody.contentType()} returns null
            every { mockResponseBody.contentLength()} returns 0

            coEvery {newsApiService.getHeadlines(BuildConfig.NEWS_KEY)} returns Response.error(errorCode,mockResponseBody)
            newsRepository = NewsRepository(newsApiService)
            newsRepository.getNews()
            assertThat(newsApiService.getHeadlines(BuildConfig.NEWS_KEY).code(),`is`(errorCode))
            newsRepository.getNews().collect {
                assertThat(it.errorCode,`is`(errorCode))
            }
        }
    }

}