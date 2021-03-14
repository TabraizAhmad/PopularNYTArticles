package com.example.popularnyarticles.ui.article.articledetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularnyarticles.R
class ArticleDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ArticleDetailFragment().apply {
            }
    }
}