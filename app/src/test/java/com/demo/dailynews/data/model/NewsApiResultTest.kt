package com.demo.dailynews.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class NewsApiResultTest {

    private var newsApiResult: NewsApiResult
    private val resultCode = "200"
    private val resultCount = 1
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
    private var resultArticles : List<Article> = arrayListOf(article)

    init {
        newsApiResult = NewsApiResult(resultCode,resultCount,resultArticles)
    }

    @Test
    fun test_NewsApiResultModel(){
        assertEquals(newsApiResult.status,resultCode)
        assertEquals(newsApiResult.totalResults,resultCount)
        assertEquals(newsApiResult.articles,resultArticles)

    }
}