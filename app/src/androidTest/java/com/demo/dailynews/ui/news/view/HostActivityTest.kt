package com.demo.dailynews.ui.news.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.demo.dailynews.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class HostActivityTest{

    @get:Rule
    val launchHostActivityScenarioRule = ActivityScenarioRule(HostActivity::class.java)

    @Test
    fun test_IsActivityInView() {
        onView(withId(R.id.home_main_root)).check(matches(isDisplayed()))
    }

    @Test
    fun test_IsNewsListFragmentLaunchedWhenHostActivityIsLaunched() {
        onView(withId(R.id.news_list_root)).check(matches(isDisplayed()))
    }

    @Test
    fun test_IsSwipeRefreshLayoutTriggeredInNewsListFragment() {
        onView(withId(R.id.swipeRefreshLayout)).check(matches(isDisplayed()))
    }
}