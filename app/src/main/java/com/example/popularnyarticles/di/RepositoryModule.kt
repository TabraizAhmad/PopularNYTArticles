package com.example.popularnyarticles.di

import com.example.popularnyarticles.network.local.PopularArticleDAO
import com.example.popularnyarticles.network.remote.PopularApiService
import com.example.popularnyarticles.network.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideArticleRepository(
        service: PopularApiService,
        popularArticleDao: PopularArticleDAO
    ): ArticleRepository {
        return ArticleRepository(service,popularArticleDao)
    }


}