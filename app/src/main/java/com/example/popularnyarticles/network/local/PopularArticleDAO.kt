package com.example.popularnyarticles.network.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.popularnyarticles.utils.Constants.POPULAR_ARTICLE_TABLE_NAME
import com.example.popularnyarticles.network.model.PopularArticle


@Dao
interface PopularArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertall(userEntity: List<PopularArticle>)



    @Query("SELECT * FROM " + POPULAR_ARTICLE_TABLE_NAME)
    fun getAllPopularNews(): List<PopularArticle>
}
