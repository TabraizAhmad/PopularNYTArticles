package com.example.popularnyarticles.network.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.popularnyarticles.utils.Constants.POPULAR_ARTICLE_TABLE_NAME
import com.example.popularnyarticles.utils.Converters
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity(tableName = POPULAR_ARTICLE_TABLE_NAME)
@TypeConverters(Converters::class)
@Parcelize
data class PopularArticle(


    @SerializedName("abstract")
    val articleAbstract:String,
    val byline: String,
    @PrimaryKey
    val id: Long,
    val media: List<Media>,
    val published_date: String,
    val title: String,
    val updated: String
):Parcelable