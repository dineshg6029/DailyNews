package com.demo.dailynews.ui.news.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.dailynews.R
import com.demo.dailynews.common.updateDateTime
import com.demo.dailynews.data.model.Article
import com.demo.dailynews.databinding.NewsItemViewBinding
import com.demo.dailynews.ui.news.interfaces.CustomItemClickListener
import javax.inject.Inject


class ArticlesListAdapter @Inject constructor() :
    RecyclerView.Adapter<ArticlesListAdapter.ArticleViewHolder>() {

    private var articles:MutableList<Article> = mutableListOf()
    var customItemClickListener: CustomItemClickListener ?= null
        get() = field
        set(value) {
            field = value
        }


    fun update(updatedArticles: List<Article>) {
        articles.clear()
        articles.addAll(updatedArticles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            NewsItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.onBind(articles[position])
    }

    override fun getItemCount() = articles.size

    inner class ArticleViewHolder(val binding: NewsItemViewBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.rootView.setOnClickListener(this)
        }

        fun onBind(article: Article) {
            article?.let {
                binding.titleTextView.text = it.newsHeadline
                binding.dateTextView.text = updateDateTime(it.newsDateTime)
                Glide.with(binding.root.context)
                    .load(it.newsImageUrl)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(android.R.drawable.stat_notify_error)
                    .into(binding.newsImageView)
            }
        }

        override fun onClick(view: View?) {
            when (view?.id) {
                R.id.rootView -> {
                    customItemClickListener?.itemClick(adapterPosition)
                }
            }
        }
    }
}