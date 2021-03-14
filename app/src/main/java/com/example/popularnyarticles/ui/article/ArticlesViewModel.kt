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

    fun getPopularArticles(section: String, period: Int,isAvailable:Boolean): LiveData<Resource<PopularArticleListResponse>> {
        return liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading())
            emit(repository.getPopularNews(section, period,isAvailable))

        }
    }


}