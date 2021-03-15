package com.example.popularnyarticles.ui.article.articlelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularnyarticles.databinding.ArticleListFragmentBinding
import com.example.popularnyarticles.extention.makeGone
import com.example.popularnyarticles.extention.makeVisible
import com.example.popularnyarticles.network.model.PopularArticle
import com.example.popularnyarticles.network.model.Resource
import com.example.popularnyarticles.ui.article.ArticlesViewModel
import com.example.popularnyarticles.ui.article.articlelist.adapter.ArticleRVAdapter
import com.example.popularnyarticles.utils.ConnectivityLiveData
import com.example.popularnyarticles.utils.Constants
import com.example.popularnyarticles.utils.Constants.SECTION
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject

@WithFragmentBindings
@AndroidEntryPoint
class ArticleListFragment : Fragment(), ArticleRVAdapter.OnItemClicked {

    private val viewModel: ArticlesViewModel by activityViewModels()
    private lateinit var binding: ArticleListFragmentBinding
    private var articles = ArrayList<PopularArticle>()
    private lateinit var articleRVAdapter:ArticleRVAdapter
    @Inject
    lateinit var connectivityLiveData: ConnectivityLiveData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ArticleListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //adding a layoutmanager
        binding.popularListRV.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        articleRVAdapter = ArticleRVAdapter(articles,context, this)
        binding.popularListRV.adapter = articleRVAdapter

        fetchPopularArticles()
    }

    private fun fetchPopularArticles(){
        connectivityLiveData.observe(viewLifecycleOwner, { isAvailable->

            viewModel.getPopularArticles(SECTION,
                Constants.PeriodFilter.SHOW_WEEKLY.value,
                isAvailable).observe(viewLifecycleOwner, { resource ->

                when (resource) {
                    is Resource.Error -> {
                        binding.popularListRV.makeGone()
                        binding.errorTextView.makeVisible()
                        binding.progressBar.makeGone()
                        binding.errorTextView.text = resource.exception.message
                    }
                    is Resource.Success -> {
                            binding.popularListRV.makeVisible()
                            binding.errorTextView.makeGone()
                            binding.progressBar.makeGone()
                            articles = resource.data?.results as ArrayList<PopularArticle>
                        articleRVAdapter.setArticles(articles)

                    }
                    is Resource.Loading ->{
                        binding.progressBar.makeVisible()
                        binding.errorTextView.makeGone()
                        binding.popularListRV.makeGone()
                    }
                }
            })

        })

    }

    override fun onItemClicked(view:View, article: PopularArticle) {
        val action = ArticleListFragmentDirections.actionArticleListFragmentToArticleDetailFragment(article)
        view.findNavController().navigate(action)
    }
}