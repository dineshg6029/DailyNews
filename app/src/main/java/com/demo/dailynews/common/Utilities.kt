package com.demo.dailynews.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.demo.dailynews.R

@BindingAdapter("loadImageUrl")
fun loadImageUrl(imageView: ImageView,imageUrl:String){
    Glide.with(imageView.context)
        .load(imageUrl)
        .placeholder(R.mipmap.ic_launcher_round)
        .error(android.R.drawable.stat_notify_error)
        .into(imageView)
}

fun updateDateTime(dateTime:String):String{
    val list: List<String> = dateTime.split("T")
    val timeList = list[1].split(".")
    return "${list[0]} ${timeList[0]}"
}