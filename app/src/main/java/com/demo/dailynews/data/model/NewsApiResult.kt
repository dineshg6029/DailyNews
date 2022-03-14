package com.demo.dailynews.data.model

data class NewsApiResult(
    var status:String,
    var totalResults:Int,
    var articles: List<Article>
)
