package com.example.popularnyarticles.network.repository



import com.example.popularnyarticles.network.local.PopularArticleDAO
import com.example.popularnyarticles.network.model.PopularArticleListResponse
import com.example.popularnyarticles.network.model.Resource
import com.example.popularnyarticles.network.remote.PopularApiService
import com.example.popularnyarticles.utils.Constants.API_KEY
import com.example.popularnyarticles.utils.Constants.OK_STATUS
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ArticleRepository @Inject constructor(
    private val apiService: PopularApiService,
    private val popularDao: PopularArticleDAO) {

    suspend fun getPopularNews(section: String,
                               period: Int,isAvailable:Boolean): Resource<PopularArticleListResponse> {
        try {
            if(isAvailable){
                val response = apiService.getPopularNews(section, period, API_KEY)
                return if (response.isSuccessful) {
                    response.body()?.results?.let { list->
                        popularDao.insertall(list)
                    }
                    Resource.Success(response.body())
                } else {
                    Resource.Error(Exception(response.message()))
                }
            }else{
                val list = popularDao.getAllPopularNews()
                return if(list.isNotEmpty()){
                    val response = PopularArticleListResponse(list,OK_STATUS)
                    Resource.Success(response)
                }else{
                    Resource.Error(Exception("No internet connection and no offline record found."))
                }
            }

        } catch (ex: Exception) {
            return Resource.Error(ex)
        }
    }
}