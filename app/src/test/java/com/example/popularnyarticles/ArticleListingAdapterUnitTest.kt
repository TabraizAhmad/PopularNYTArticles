package com.example.popularnyarticles

import android.content.Context
import com.example.popularnyarticles.ui.article.articlelist.adapter.ArticleRVAdapter
import com.example.popularnyarticles.utils.MockUtils
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ArticleListingAdapterUnitTest {
    @Mock
    var context: Context? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun itemCount() {
        val itemsCount = 3
        val adapter = ArticleRVAdapter(MockUtils.createListOfArticles(itemsCount),context,null)
        assertEquals(itemsCount, adapter.itemCount)
    }
}