package com.demo.dailynews.data.model

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class ArticleTest {

    val newsHeadline = "HeadLine"
    val newsDescription = "Description"
    val newsUrl = "newsUrl"
    val newsImageUrl = "imageUrl"
    val newsDateTime ="dateTime"

    var article: Article = Article(
        newsHeadline,
        newsDescription,
        newsUrl,
        newsImageUrl,
        newsDateTime)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_model(){
        assertEquals(article.newsHeadline,newsHeadline)
        assertEquals(article.newsDescription,newsDescription)
        assertEquals(article.newsUrl,newsUrl)
        assertEquals(article.newsImageUrl,newsImageUrl)
        assertEquals(article.newsDateTime,newsDateTime)
    }
}