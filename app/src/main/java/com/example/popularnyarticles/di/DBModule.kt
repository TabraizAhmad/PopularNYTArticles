package com.example.popularnyarticles.di

import android.app.Application
import com.example.popularnyarticles.network.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Singleton
    @Provides
    fun providesDatabase(application: Application) = AppDatabase.invoke(application)

    @Singleton
    @Provides
    fun providesPopularArticleDAO(database: AppDatabase) = database.popularArticleDAO()

}