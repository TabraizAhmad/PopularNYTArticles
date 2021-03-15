package com.example.popularnyarticles.di

import android.app.Application
import com.example.popularnyarticles.BuildConfig
import com.example.popularnyarticles.network.remote.PopularApiService
import com.example.popularnyarticles.system.MyApp
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {


        val builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS)


        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)

        }
        return builder.build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(application: Application,client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl( (application as MyApp).getBaseUrl())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providePopularApiService(retrofit: Retrofit): PopularApiService {
        return retrofit.create(PopularApiService::class.java)
    }
}