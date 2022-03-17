package com.demo.dailynews.ui.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.demo.dailynews.R
import com.demo.dailynews.data.NO_INTERNET_CONNECTION
import com.demo.dailynews.data.Resource
import com.demo.dailynews.data.model.Article
import com.demo.dailynews.data.model.NewsApiResult
import com.demo.dailynews.databinding.NewsListFragmentBinding
import com.demo.dailynews.ui.news.adapters.ArticlesListAdapter
import com.demo.dailynews.ui.news.interfaces.CustomItemClickListener
import com.demo.dailynews.ui.news.viewmodel.NewsListViewModel
import com.google.android.material.snackbar.Snackbar
import com.java.predictweather.data.retrofit.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment : Fragment(), CustomItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: NewsListViewModel by viewModels()

    @Inject
    lateinit var articlesListAdapter: ArticlesListAdapter

    @Inject
    lateinit var networkUtils: NetworkUtils
    private lateinit var dataBindingObject: NewsListFragmentBinding
    private var articles: MutableList<Article>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DataBindingUtil.inflate<NewsListFragmentBinding>(inflater, R.layout.news_list_fragment, container, false)
            .also { dataBindingObject = it }
        return dataBindingObject.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBindingObject.swipeRefreshLayout.setOnRefreshListener(this)
        observeLiveData()
        loadRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.articleResourceResult.value == null)
            fetchData()

        viewModel.articleResourceResult.value?.data?.articles?.let {
            articlesListAdapter.update(it)
        }
    }

    private fun loadRecyclerView() {
        articlesListAdapter.customItemClickListener = this
        dataBindingObject.articlesRecyclerView.layoutManager = LinearLayoutManager(context)
        dataBindingObject.articlesRecyclerView.setHasFixedSize(false)
        dataBindingObject.articlesRecyclerView.adapter = articlesListAdapter
    }

    private fun observeLiveData() {

        viewModel.articleResourceResult.observe(this,{
            when(it){
                is Resource.Loading<NewsApiResult> -> {
                    dataBindingObject.swipeRefreshLayout.isRefreshing = true
                    dataBindingObject.errorTextView.visibility = View.GONE
                    dataBindingObject.articlesRecyclerView.visibility = View.GONE
                }
                is Resource.DataError<NewsApiResult> -> {
                    dataBindingObject.swipeRefreshLayout.isRefreshing = false
                    if (it.errorCode != NO_INTERNET_CONNECTION) {
                        with(dataBindingObject.errorTextView) {
                            visibility = View.VISIBLE
                            text = getString(R.string.fetch_failed_message)
                        }
                    } else {
                        Snackbar.make(
                            dataBindingObject.swipeRefreshLayout,
                            getString(R.string.internet_error),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                is Resource.Success<NewsApiResult> -> {
                    dataBindingObject.swipeRefreshLayout.isRefreshing = false
                    val newsApiResult = it.data
                    newsApiResult?.let{ apiResult ->
                        if (!apiResult.articles.isNullOrEmpty()) {
                            dataBindingObject.articlesRecyclerView.visibility = View.VISIBLE
                            articles = apiResult.articles.toMutableList()
                            articlesListAdapter.update(articles!!)
                        } else {
                            dataBindingObject.errorTextView.visibility = View.VISIBLE
                            dataBindingObject.errorTextView.text = getString(R.string.no_news_found)
                        }
                    }
                }
            }
        })
    }

    override fun itemClick(position: Int) {
        articles?.let {
            val directions =
                NewsListFragmentDirections.actionNewsListFragmentToNewsDescription(
                    it[position]
                )
            findNavController().navigate(directions)
        }
    }

    override fun onRefresh() {
        fetchData()
    }

    private fun fetchData() {
        viewModel.getNewsArticles()
    }

}