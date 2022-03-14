package com.demo.dailynews.data.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title")
    var newsHeadline:String,
    @SerializedName("description")
    var newsDescription:String,
    @SerializedName("url")
    var newsUrl:String,
    @SerializedName("urlToImage")
    var newsImageUrl:String,
    @SerializedName("publishedAt")
    var newsDateTime:String
)
