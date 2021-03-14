package com.example.popularnyarticles.ui.article.articlelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.popularnyarticles.databinding.ArticleListFragmentBinding
import com.example.popularnyarticles.extention.makeGone
import com.example.popularnyarticles.extention.makeVisible
import com.example.popularnyarticles.network.model.Resource
import com.example.popularnyarticles.ui.article.ArticlesViewModel
import com.example.popularnyarticles.utils.ConnectivityLiveData
import com.example.popularnyarticles.utils.Constants
import com.example.popularnyarticles.utils.Constants.SECTION
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import javax.inject.Inject

@WithFragmentBindings
@AndroidEntryPoint
class ArticleListFragment : Fragment() {

    private val viewModel: ArticlesViewModel by activityViewModels()
    private lateinit var binding: ArticleListFragmentBinding

    @Inject
    lateinit var connectivityLiveData: ConnectivityLiveData

    companion object {
        fun newInstance() = ArticleListFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ArticleListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchPopularArticles()
    }

    private fun fetchPopularArticles(){
        connectivityLiveData.observe(viewLifecycleOwner, Observer {isAvailable->

            viewModel.getPopularArticles(SECTION,
                Constants.PeriodFilter.SHOW_WEEKLY.value,
                isAvailable).observe(viewLifecycleOwner, { resource ->

                when (resource) {
                    is Resource.Error -> {
                        binding.popularList.makeGone()
                        binding.errorTextView.makeVisible()
                        binding.progressBar.makeGone()
                        binding.errorTextView.text = resource.exception.message
                    }
                    is Resource.Success -> {
                            binding.popularList.makeVisible()
                            binding.errorTextView.makeGone()
                            binding.progressBar.makeGone()
                            resource.data
                    }
                    is Resource.Loading ->{
                        binding.progressBar.makeVisible()
                        binding.errorTextView.makeGone()

                    }
                }
            })

        })

    }
}