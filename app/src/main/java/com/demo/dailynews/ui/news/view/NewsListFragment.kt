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
import com.demo.dailynews.data.model.Article
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
    @Inject lateinit var articlesListAdapter: ArticlesListAdapter
    @Inject lateinit var networkUtils: NetworkUtils
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
        if (viewModel.articleList.value == null) fetchData()
        viewModel.articleList.value?.let {
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
        viewModel.loadingStatus.observe(this, {
            if (it) {
                dataBindingObject.swipeRefreshLayout.isRefreshing = true
                dataBindingObject.errorTextView.visibility = View.GONE
                dataBindingObject.articlesRecyclerView.visibility = View.GONE
            } else {
                dataBindingObject.swipeRefreshLayout.isRefreshing = false
            }
        })

        viewModel.errorMessage.observe(
            this,
            {
                dataBindingObject.swipeRefreshLayout.isRefreshing = false
                with(dataBindingObject.errorTextView){
                    visibility = View.VISIBLE
                    text = it
                }
            },
        )

        viewModel.articleList.observe(this, {
            dataBindingObject.swipeRefreshLayout.isRefreshing = false
            if (!it.isNullOrEmpty()) {
                dataBindingObject.articlesRecyclerView.visibility = View.VISIBLE
                articles = it.toMutableList()
                articlesListAdapter.update(it)
            } else {
                dataBindingObject.errorTextView.visibility = View.VISIBLE
                dataBindingObject.errorTextView.text = getString(R.string.no_news_found)
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
        if (networkUtils.isConnected()) {
            viewModel.getNewsArticles()
        } else {
            Snackbar.make(
                dataBindingObject.swipeRefreshLayout,
                getString(R.string.internet_error),
                Snackbar.LENGTH_LONG
            ).show()
            dataBindingObject.swipeRefreshLayout.isRefreshing = false
        }
    }

}