package com.demo.dailynews.ui.news.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.demo.dailynews.R
import com.demo.dailynews.databinding.NewsDescriptionFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDescription : Fragment() {
    private val arguments by navArgs<ArticleDescriptionArgs>()
    private lateinit var binding: NewsDescriptionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.news_description_fragment, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        arguments.let {
            binding.currentArticle = it.article
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.shareNewsURL ->{
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.let {
                    it.type = "text/plain"
                    it.putExtra(Intent.EXTRA_TEXT,binding.currentArticle?.newsUrl)
                    startActivity(Intent.createChooser(it,getString(R.string.share_url)))
                }
                return true
            }
        }
        return false
    }

}