package com.demo.dailynews.ui.news.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.dailynews.data.repository.NewsRepository

class NewsListViewModelFactory(private val app:Application,private val newsRepository: NewsRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass == NewsListViewModel::class.java){
            return NewsListViewModel(app,newsRepository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}