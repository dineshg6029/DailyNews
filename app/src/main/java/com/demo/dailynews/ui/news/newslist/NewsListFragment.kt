package com.demo.dailynews.ui.news.newslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.dailynews.R
import com.demo.dailynews.data.model.Article
import com.demo.dailynews.data.repository.NewsRepository
import com.demo.dailynews.data.retrofit.NewsApiService
import com.demo.dailynews.databinding.NewsListFragmentBinding
import com.demo.dailynews.ui.interfaces.CustomItemClickListener
import com.demo.dailynews.ui.news.adapters.ArticlesListAdapter
import com.demo.dailynews.ui.news.newslist.viewmodel.NewsListViewModel
import com.demo.dailynews.ui.news.newslist.viewmodel.NewsListViewModelFactory

class NewsListFragment : Fragment(),CustomItemClickListener {

    private lateinit var viewModel: NewsListViewModel
    private lateinit var dataBindingObject: NewsListFragmentBinding
    private lateinit var articlesListAdapter: ArticlesListAdapter
    private var articles:MutableList<Article> ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBindingObject =
            DataBindingUtil.inflate(inflater, R.layout.news_list_fragment, container, false)
        return dataBindingObject.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val newsApiService = NewsApiService()
        val newsRepository = NewsRepository(newsApiService!!)
        val viewModelFactory = NewsListViewModelFactory(activity!!.application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsListViewModel::class.java)
        observeLiveData()
        loadRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNewsArticles()
    }

    private fun loadRecyclerView(){
        articlesListAdapter = ArticlesListAdapter()
        dataBindingObject.articlesRecyclerView.layoutManager = LinearLayoutManager(context)
        dataBindingObject.articlesRecyclerView.setHasFixedSize(false)
        dataBindingObject.articlesRecyclerView.adapter = articlesListAdapter
    }

    private fun observeLiveData() {
        viewModel.loadingStatus.observe(this, {
            if (it) {
                dataBindingObject.loadingBar.visibility = View.VISIBLE
                dataBindingObject.errorTextView.visibility = View.GONE
                dataBindingObject.articlesRecyclerView.visibility = View.GONE
            } else {
                dataBindingObject.loadingBar.visibility = View.GONE
            }
        })

        viewModel.errorMessage.observe(this, {
            dataBindingObject.errorTextView.visibility = View.VISIBLE
            dataBindingObject.errorTextView.text = it
        })

        viewModel.articleList.observe(this, {
            if(!it.isNullOrEmpty()){
                dataBindingObject.articlesRecyclerView.visibility = View.VISIBLE
                articles = it?.toMutableList()
                articlesListAdapter.update(it)
            }else{
                dataBindingObject.errorTextView.visibility = View.VISIBLE
                dataBindingObject.errorTextView.text = getString(R.string.no_news_found)
            }
        })
    }

    override fun itemClick(position: Int) {

    }

}