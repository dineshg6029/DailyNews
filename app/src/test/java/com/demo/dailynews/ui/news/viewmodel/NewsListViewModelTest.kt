package com.demo.dailynews.ui.news.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.demo.dailynews.data.Resource
import com.demo.dailynews.data.Resource.Loading
import com.demo.dailynews.data.model.Article
import com.demo.dailynews.data.model.NewsApiResult
import com.demo.dailynews.data.repository.NewsRepository
import com.demo.dailynews.utils.MainCoroutineRule
import com.java.predictweather.data.retrofit.NetworkUtils
import com.util.InstantExecutorExtension
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
@RunWith(MockitoJUnitRunner::class)
class NewsListViewModelTest {
    // Subject under test
    private lateinit var newsListViewModel: NewsListViewModel

    // Use a fake UseCase to be injected into the viewModel
    private val newsRepository: NewsRepository = mockk()
    private val networkUtils : NetworkUtils = mockk()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val newsObserver: Observer<Resource<NewsApiResult>> = mockk()



    lateinit var newsApiResult: NewsApiResult
    val newsHeadline = "HeadLine"
    val newsDescription = "Description"
    val newsUrl = "newsUrl"
    val newsImageUrl = "imageUrl"
    val newsDateTime ="dateTime"

    lateinit var article: Article
    init {
        article = Article(
            newsHeadline,
            newsDescription,
            newsUrl,
            newsImageUrl,
            newsDateTime)
        newsApiResult = NewsApiResult("ok",1, arrayListOf(article))

    }



    @Test
    fun get_News_List_Success() {
        //1- Mock calls
        every { networkUtils.isConnected() } returns true

        coEvery { newsRepository.getNews() } returns flow {
            emit(Resource.Success(newsApiResult))
        }

        Assert.assertEquals(newsApiResult.articles[0],article)

        //2-Call
        newsListViewModel = NewsListViewModel(newsRepository,networkUtils)
        newsListViewModel.getNewsArticles()
        //active observer for livedata
        newsListViewModel.articleResourceResult.observeForever {
        }
        Thread.sleep(2000L)
        //3-verify
        val isEmptyList = newsListViewModel.articleResourceResult.value?.data?.articles.isNullOrEmpty()
        assertEquals(newsApiResult, newsListViewModel.articleResourceResult.value?.data)
        Assert.assertEquals(false, isEmptyList)
    }


    @Test
    fun get_News_List_Loading() {
        val loadResult:Resource<NewsApiResult> = Loading()
        //1- Mock calls
        every { networkUtils.isConnected() } returns true

        coEvery { newsRepository.getNews() } returns flow {
            emit(Resource.Success(newsApiResult))
        }


        //2-Call
        newsListViewModel = NewsListViewModel(newsRepository,networkUtils)
        newsListViewModel.getNewsArticles()
        //active observer for livedata
        newsListViewModel.articleResourceResult.observeForever {
        }
        //3-verify
        val isEmptyList = newsListViewModel.articleResourceResult.value?.data?.articles.isNullOrEmpty()
        Assert.assertEquals(true, isEmptyList)
    }
    @Test
    fun get_News_List_Failure() {
        val dataErrorResult:Resource<NewsApiResult> = Resource.DataError(102)        //1- Mock calls
        every { networkUtils.isConnected() } returns true

        coEvery { newsRepository.getNews() } returns flow {
            emit(dataErrorResult)
        }


        //2-Call
        newsListViewModel = NewsListViewModel(newsRepository,networkUtils)
        newsListViewModel.getNewsArticles()
        //active observer for livedata
        newsListViewModel.articleResourceResult.observeForever {
        }
        Thread.sleep(2000L)
        //3-verify
        val isEmptyList = newsListViewModel.articleResourceResult.value?.data?.articles.isNullOrEmpty()
        assertEquals(dataErrorResult.errorCode, newsListViewModel.articleResourceResult.value?.errorCode)
        Assert.assertEquals(true, isEmptyList)
    }
}