package com.example.popularnyarticles.network.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.popularnyarticles.utils.Constants.ARTICLE_DB
import com.example.popularnyarticles.network.model.PopularArticle

@Database(entities = [PopularArticle::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun popularArticleDAO(): PopularArticleDAO

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, ARTICLE_DB)
            .allowMainThreadQueries()
            .build()

    }
}