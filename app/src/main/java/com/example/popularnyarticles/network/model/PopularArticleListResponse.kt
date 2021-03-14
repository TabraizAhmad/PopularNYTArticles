package com.example.popularnyarticles.network.model

data class PopularArticleListResponse(
    val results: List<PopularArticle>,
    val status: String
)