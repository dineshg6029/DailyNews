package com.demo.dailynews.ui.news.articledescription

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.demo.dailynews.R
import com.demo.dailynews.ui.news.articledescription.viewmodel.ArticleDescriptionViewModel

class ArticleDescription : Fragment() {

    companion object {
        fun newInstance() = ArticleDescription()
    }

    private lateinit var viewModel: ArticleDescriptionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_description_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticleDescriptionViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onResume() {
        super.onResume()
        with(activity) {
            Toast.makeText(this,"Launched News Description",Toast.LENGTH_LONG).show()
        }
    }

}