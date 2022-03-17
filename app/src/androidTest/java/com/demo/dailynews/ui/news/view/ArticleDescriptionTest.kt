package com.demo.dailynews.ui.news.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.demo.dailynews.R
import com.demo.dailynews.ui.news.adapters.ArticlesListAdapter.ArticleViewHolder
import com.demo.dailynews.utils.EspressoIdlingResourceRule
import com.demo.dailynews.utils.SHORT_TIME_SLEEP
import com.demo.dailynews.utils.TextViewTextIsNotEmptyAssertion
import com.java.predictweather.data.retrofit.NetworkUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4ClassRunner::class)
class ArticleDescriptionTest{

    @get:Rule
    val hostActivityLaunchRule = ActivityScenarioRule(HostActivity::class.java)

    @get:Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    private lateinit var networkUtils: NetworkUtils

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        networkUtils = NetworkUtils(context)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_ArticleDescriptionFragmentLaunched() {
        if(networkUtils.isConnected()){
            onView(withId(R.id.articlesRecyclerView))
                .check(matches(isDisplayed()))
            onView(withId(R.id.articlesRecyclerView))
                .perform(actionOnItemAtPosition<ArticleViewHolder>(
                    0, click()))
            onView(withId(R.id.news_description_root))
                .check(matches(isDisplayed()))
        }else{
            assertThat(networkUtils.isConnected(), CoreMatchers.`is`(false))
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(SHORT_TIME_SLEEP)
    }


    @Test
    fun test_IsHeadlineDisplayedInArticleDescriptionFragment() {
        if(networkUtils.isConnected()){
            onView(withId(R.id.articlesRecyclerView))
                .check(matches(isDisplayed()))
            onView(withId(R.id.articlesRecyclerView))
                .perform(actionOnItemAtPosition<ArticleViewHolder>(
                    0, click()))
            onView(withId(R.id.title_textView))
                .check(matches(isDisplayed()))
        }else{
            assertThat(networkUtils.isConnected(), CoreMatchers.`is`(false))
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(SHORT_TIME_SLEEP)
    }

    @Test
    fun test_isDescriptionDisplayedInArticleDescriptionFragment() {
        if(networkUtils.isConnected()) {
            onView(withId(R.id.articlesRecyclerView))
                .check(matches(isDisplayed()))
            onView(withId(R.id.articlesRecyclerView))
                .perform(
                    actionOnItemAtPosition<ArticleViewHolder>(
                        0, click()
                    )
                )
            onView(withId(R.id.description_TextView))
                .check(matches(isDisplayed()))
        }else{
            assertThat(networkUtils.isConnected(), CoreMatchers.`is`(false))
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(SHORT_TIME_SLEEP)
    }

    @Test
    fun test_isDateTimeDisplayedInArticleDescriptionFragment() {
        if(networkUtils.isConnected()) {
            onView(withId(R.id.articlesRecyclerView))
                .check(matches(isDisplayed()))
            onView(withId(R.id.articlesRecyclerView))
                .perform(
                    actionOnItemAtPosition<ArticleViewHolder>(
                        0, click()
                    )
                )
            onView(withId(R.id.date_TextView))
                .check(matches(isDisplayed()))
        }else{
            assertThat(networkUtils.isConnected(), CoreMatchers.`is`(false))
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(SHORT_TIME_SLEEP)
    }


    @Test
    fun test_CheckHeadlineTextInArticleDescriptionFragment() {
        if(networkUtils.isConnected()) {
            onView(withId(R.id.articlesRecyclerView))
                .check(matches(isDisplayed()))
            onView(withId(R.id.articlesRecyclerView))
                .perform(
                    actionOnItemAtPosition<ArticleViewHolder>(
                        0, click()
                    )
                )
            onView(withId(R.id.title_textView))
                .check(matches(isDisplayed()))
            onView(withId(R.id.title_textView))
                .check(TextViewTextIsNotEmptyAssertion())
        }else{
            assertThat(networkUtils.isConnected(), CoreMatchers.`is`(false))
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(SHORT_TIME_SLEEP)
    }

    @Test
    fun test_CheckDescriptionTextInArticleDescriptionFragment() {
        if(networkUtils.isConnected()) {
            onView(withId(R.id.articlesRecyclerView))
                .check(matches(isDisplayed()))
            onView(withId(R.id.articlesRecyclerView))
                .perform(
                    actionOnItemAtPosition<ArticleViewHolder>(
                        0, click()
                    )
                )
            onView(withId(R.id.description_TextView))
                .check(matches(isDisplayed()))
            onView(withId(R.id.description_TextView))
                .check(TextViewTextIsNotEmptyAssertion())
        }else{
            assertThat(networkUtils.isConnected(), CoreMatchers.`is`(false))
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(SHORT_TIME_SLEEP)
    }

    @Test
    fun test_CheckDateTimeTextInArticleDescriptionFragment() {
        if(networkUtils.isConnected()) {
            onView(withId(R.id.articlesRecyclerView))
                .check(matches(isDisplayed()))
            onView(withId(R.id.articlesRecyclerView))
                .perform(
                    actionOnItemAtPosition<ArticleViewHolder>(
                        0, click()
                    )
                )
            onView(withId(R.id.date_TextView))
                .check(matches(isDisplayed()))
            onView(withId(R.id.date_TextView))
                .check(TextViewTextIsNotEmptyAssertion())
        }else{
            assertThat(networkUtils.isConnected(), CoreMatchers.`is`(false))
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(SHORT_TIME_SLEEP)
    }

    @Test
    fun test_isNewsShareUrlDisplayedInArticleDescriptionFragment() {
        if(networkUtils.isConnected()) {

            onView(withId(R.id.articlesRecyclerView))
                .check(matches(isDisplayed()))
            onView(withId(R.id.articlesRecyclerView))
                .perform(
                    actionOnItemAtPosition<ArticleViewHolder>(
                        0, click()
                    )
                )
            onView(withId(R.id.shareNewsURL))
                .check(matches(isDisplayed()))
        }else{
            assertThat(networkUtils.isConnected(), CoreMatchers.`is`(false))
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(SHORT_TIME_SLEEP)
    }

    @Test
    fun test_IsNewsShareNewsUrlActionInArticleDescriptionFragment() {
        if(networkUtils.isConnected()) {
            onView(withId(R.id.articlesRecyclerView))
                .check(matches(isDisplayed()))
            onView(withId(R.id.articlesRecyclerView))
                .perform(
                    actionOnItemAtPosition<ArticleViewHolder>(
                        0, click()
                    )
                )
            onView(withId(R.id.shareNewsURL))
                .check(matches(isDisplayed()))
            onView(withId(R.id.shareNewsURL))
                .perform(click())
        }else{
            assertThat(networkUtils.isConnected(), CoreMatchers.`is`(false))
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(SHORT_TIME_SLEEP)
    }
}