package com.example.popularnyarticles.network.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.popularnyarticles.network.model.Media
import com.example.popularnyarticles.network.model.MediaMetadata
import com.example.popularnyarticles.network.model.PopularArticle
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PopularArticleDAOTest {

    private lateinit var db:AppDatabase
    private lateinit var popularArticleDAO: PopularArticleDAO
    @Before
    fun setup(){
        db = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase::class.java
        ).allowMainThreadQueries().build()
        popularArticleDAO = db.popularArticleDAO()
    }

    @After
    fun teardown(){
        db.close()
    }

    @Test
    fun testInsertAll(){
        //will insert one value and check if inserted and getallpopularNews will be test as well
        val mediaMedia = MediaMetadata("https://upload.wikimedia.org/wikipedia/commons/9/9c/-UNGA_%2848784380531%29_%28cropped%29.jpg")
        val media = Media(listOf(mediaMedia),"photo")
        val popularArticle = PopularArticle("pti won senate chairman election",
                "tabraiz",1, listOf(media),
                "2021-03-11","Pakistan chairman senate election","2021-03-15 13:22:00")
        popularArticleDAO.insertall(listOf(popularArticle))
        val articles = popularArticleDAO.getAllPopularNews()

            assertTrue(articles.contains(popularArticle))

    }
}