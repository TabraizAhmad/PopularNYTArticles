package com.example.popularnyarticles.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.popularnyarticles.network.model.PopularArticleListResponse
import com.example.popularnyarticles.network.repository.ArticleRepository
import com.example.popularnyarticles.network.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

     private var articleResponse: Resource<PopularArticleListResponse>? = null


    fun getPopularArticles(section: String, period: Int,isAvailable:Boolean): LiveData<Resource<PopularArticleListResponse>> {
        return liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            if(articleResponse== null){
                emit(Resource.Loading())
                articleResponse = repository.getPopularNews(section, period,isAvailable)
            }
                articleResponse?.let { emit(it) }

        }
    }


}