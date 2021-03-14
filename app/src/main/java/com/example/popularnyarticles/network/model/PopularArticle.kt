package com.example.popularnyarticles.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.popularnyarticles.utils.Constants.POPULAR_ARTICLE_TABLE_NAME
import com.example.popularnyarticles.utils.Converters
import com.google.gson.annotations.SerializedName


@Entity(tableName = POPULAR_ARTICLE_TABLE_NAME)
@TypeConverters(Converters::class)
data class PopularArticle(


    @SerializedName("abstract")
    val articleAbstract:String,
    val adx_keywords: String,
    val asset_id: Long,
    val byline: String,
    @PrimaryKey
    val id: Long,
    val media: List<Media>,
    val nytdsection: String,
    val published_date: String,
    val section: String,
    val source: String,
    val subsection: String,
    val title: String,
    val type: String,
    val updated: String,
    val uri: String,
    val url: String
)