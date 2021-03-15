package com.example.popularnyarticles.ui.article.articledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.popularnyarticles.R
import com.example.popularnyarticles.databinding.ArticleListFragmentBinding
import com.example.popularnyarticles.databinding.FragmentArticleDetailBinding
import com.example.popularnyarticles.extention.getPhotoUrl

class ArticleDetailFragment : Fragment() {

    val args: ArticleDetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentArticleDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.Article
        article?.let { item->
            binding.articleAbstract.text = item.articleAbstract
            binding.articleTitle.text = item.title
            binding.publishedDate.text = getString(R.string.published_date)+item.published_date
            binding.updatedDate.text = getString(R.string.updateed_date)+ item.updated

            val photoURL = item.getPhotoUrl()
            context?.let {
                Glide.with(it)
                    .load(photoURL)
                    .placeholder(R.drawable.placeholder_image)
                    .into(binding.mediaImage)

            }

        }
    }
}