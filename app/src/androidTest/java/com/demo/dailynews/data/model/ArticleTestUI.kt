package com.demo.dailynews.data.model

import android.os.Parcel
import androidx.test.core.app.ActivityScenario
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.demo.dailynews.ui.news.view.HostActivity
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ArticleTestUI{

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


    @Test
    fun test_article_is_parcelable() {
        ActivityScenario.launch(HostActivity::class.java)
        val parcel = Parcel.obtain()
        article.writeToParcel(parcel, article.describeContents())
        parcel.setDataPosition(0)
        val createdFromParcel: Article = Article.CREATOR.createFromParcel(parcel)
        assertThat(createdFromParcel.newsHeadline, `is`(newsHeadline))
        assertThat(createdFromParcel.newsDescription, `is`(newsDescription))
        assertThat(createdFromParcel.newsUrl, `is`(newsUrl))
        assertThat(createdFromParcel.newsImageUrl, `is`(newsImageUrl))
        assertThat(createdFromParcel.newsDateTime, `is`(newsDateTime))
    }

}