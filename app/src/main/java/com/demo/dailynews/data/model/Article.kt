package com.demo.dailynews.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title")
    var newsHeadline: String,
    @SerializedName("description")
    var newsDescription: String,
    @SerializedName("url")
    var newsUrl: String,
    @SerializedName("urlToImage")
    var newsImageUrl: String,
    @SerializedName("publishedAt")
    var newsDateTime: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, p1: Int) {
        dest?.writeString(newsHeadline)
        dest?.writeString(newsDescription)
        dest?.writeString(newsUrl)
        dest?.writeString(newsImageUrl)
        dest?.writeString(newsDateTime)
    }

    companion object {
        @JvmField
        final val CREATOR: Parcelable.Creator<Article> = object : Parcelable.Creator<Article> {
            override fun createFromParcel(source: Parcel): Article {
                return Article(source)
            }

            override fun newArray(size: Int): Array<Article?> {
                return arrayOfNulls(size)
            }
        }
    }


}
