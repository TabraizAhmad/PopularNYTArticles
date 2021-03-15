package com.example.popularnyarticles

import com.example.popularnyarticles.utils.MockUtils
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ArticleListingAdapterUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun itemCount() {
        val itemsCount = 3
        val adapter =
            MockUtils.getArticleRVAdapter(3)
        assertEquals(itemsCount, adapter.itemCount)
    }
}