package com.example.popularnyarticles.network.remote

import com.example.popularnyarticles.network.model.PopularArticleListResponse
import retrofit2.Response
import retrofit2.http.*

interface PopularApiService {



    @GET("mostpopular/v2/{section}/{period}.json")
    suspend fun getPopularNews(
        @Path("section") section: String,
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ):  Response<PopularArticleListResponse>
}