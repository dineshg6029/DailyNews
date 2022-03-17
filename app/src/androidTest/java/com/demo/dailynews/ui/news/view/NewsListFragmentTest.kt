package com.demo.dailynews.ui.news.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.demo.dailynews.R
import com.demo.dailynews.ui.news.adapters.ArticlesListAdapter
import com.demo.dailynews.utils.EspressoIdlingResourceRule
import com.demo.dailynews.utils.RecyclerViewIsNotEmptyAssertion
import com.demo.dailynews.utils.TIME_SLEEP
import com.java.predictweather.data.retrofit.NetworkUtils
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4ClassRunner::class)
class NewsListFragmentTest{

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
    fun testInternet(){
        if(networkUtils.isConnected()) {
            assertThat(networkUtils.isConnected(), `is`(true))
        }else{
            assertThat(networkUtils.isConnected(), `is`(false))
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(TIME_SLEEP)
    }

    @Test
    fun test_IsNewsListDisplayed() {
        onView(withId(R.id.articlesRecyclerView)).check(matches(isDisplayed()))
        sleep(TIME_SLEEP)
    }

    @Test
    fun test_IsNewsListDisplayedWithItems() {
        onView(withId(R.id.articlesRecyclerView)).check(matches(isDisplayed()))
        if(networkUtils.isConnected()) {
            onView(withId(R.id.articlesRecyclerView)).check(RecyclerViewIsNotEmptyAssertion())
        }else{
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(TIME_SLEEP)
    }

    @Test
    fun test_IsNewsListItemClicked() {
        onView(withId(R.id.articlesRecyclerView)).check(matches(isDisplayed()))
        sleep(TIME_SLEEP)
        if(networkUtils.isConnected()){
            // Click list item #LIST_ITEM_IN_TEST
            onView(withId(R.id.articlesRecyclerView))
                .perform(actionOnItemAtPosition<ArticlesListAdapter.ArticleViewHolder>(0, click()))
        }else{
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(TIME_SLEEP)
    }

    @Test
    fun test_selectListItem_isNewsDetailFragmentVisible() {
        onView(withId(R.id.articlesRecyclerView)).check(matches(isDisplayed()))
        sleep(TIME_SLEEP)
        if(networkUtils.isConnected()){
            // Click list item #LIST_ITEM_IN_TEST
            onView(withId(R.id.articlesRecyclerView))
                .perform(actionOnItemAtPosition<ArticlesListAdapter.ArticleViewHolder>(0, click()))
            onView(withId(R.id.news_description_root)).check(matches(isDisplayed()))
        }else{
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(TIME_SLEEP)
    }

    @Test
    fun test_pullSwipeRefresh(){
        onView(withId(R.id.swipeRefreshLayout)).perform(swipeDown())
        if(networkUtils.isConnected()) {
            sleep(TIME_SLEEP)
            onView(withId(R.id.articlesRecyclerView)).check(matches(isDisplayed()))
        }else{
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
            sleep(TIME_SLEEP)
        }
    }

    @Test
    fun test_pullSwipeRefreshAndLaunchNewsDetailFragment(){
        onView(withId(R.id.swipeRefreshLayout)).perform(swipeDown())
        sleep(TIME_SLEEP)
        if(networkUtils.isConnected()){
            onView(withId(R.id.articlesRecyclerView)).check(matches(isDisplayed()))
            sleep(TIME_SLEEP)
            // Click list item #LIST_ITEM_IN_TEST
            onView(withId(R.id.articlesRecyclerView))
                .perform(actionOnItemAtPosition<ArticlesListAdapter.ArticleViewHolder>(0, click()))
            onView(withId(R.id.news_description_root)).check(matches(isDisplayed()))
        }else{
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.internet_error)))
        }
        sleep(TIME_SLEEP)
    }
}