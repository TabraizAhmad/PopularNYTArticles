package com.example.popularnyarticles.utils

import com.example.popularnyarticles.network.model.PopularArticle
import com.example.popularnyarticles.ui.article.articlelist.adapter.ArticleRVAdapter
import org.junit.runner.manipulation.Ordering
import org.mockito.Mock
import org.mockito.Mockito
import java.util.*


class MockUtils {
    companion object{
        fun createListOfArticles(count: Int): List<PopularArticle> {
            val articleList = ArrayList<PopularArticle>()
            for (i in 0 until count) {
                articleList.add(Mockito.mock(PopularArticle::class.java))
            }
            return articleList
        }

        fun getArticleRVAdapter(
            count: Int,
        ): ArticleRVAdapter {
            val adapter = Mockito.mock(ArticleRVAdapter::class.java)
            adapter.setArticles(createListOfArticles(count))
            return adapter
        }

    }
}