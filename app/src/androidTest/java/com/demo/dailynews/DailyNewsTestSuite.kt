package com.demo.dailynews

import com.demo.dailynews.data.model.ArticleTestUI
import com.demo.dailynews.ui.news.view.ArticleDescriptionTest
import com.demo.dailynews.ui.news.view.HostActivityTest
import com.demo.dailynews.ui.news.view.NewsListFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    ArticleTestUI::class,
    HostActivityTest::class,
    NewsListFragmentTest::class,
    ArticleDescriptionTest::class
)
class DailyNewsTestSuite