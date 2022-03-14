package com.demo.dailynews.ui.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.demo.dailynews.R
import com.demo.dailynews.databinding.NewsDescriptionFragmentBinding

class ArticleDescription : Fragment() {
    private val arguments by navArgs<ArticleDescriptionArgs>()
    private lateinit var binding: NewsDescriptionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.news_description_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        arguments?.let {
            binding.currentArticle = it.article
        }
    }

}