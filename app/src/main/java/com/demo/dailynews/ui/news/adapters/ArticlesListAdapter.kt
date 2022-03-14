package com.demo.dailynews.ui.news.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.dailynews.R
import com.demo.dailynews.data.model.Article
import com.demo.dailynews.databinding.NewsItemViewBinding

class ArticlesListAdapter : RecyclerView.Adapter<ArticlesListAdapter.ArticleViewHolder>(){

    private var articles = mutableListOf<Article>()


    fun update( updatedArticles : List<Article>){
        articles.clear()
        articles.addAll(updatedArticles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = NewsItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.onBind(articles[position])
    }

    override fun getItemCount() = articles.size

    class ArticleViewHolder(val binding: NewsItemViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(article : Article){
            article?.let {
                binding.titleTextView.text = it.newsHeadline
                binding.dateTextView.text = it.newsDateTime
                Glide.with(binding.root.context)
                    .load(it.newsImageUrl)
                    .placeholder(android.R.drawable.progress_horizontal)
                    .error(android.R.drawable.stat_notify_error)
                    .into(binding.newsImageView)
            }
        }
    }
}