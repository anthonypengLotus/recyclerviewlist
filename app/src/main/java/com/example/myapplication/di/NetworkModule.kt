package com.example.myapplication.di

import com.example.myapplication.api.ArticleService
import com.example.myapplication.api.ShopService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideService(): ShopService {
        return ShopService.create()
    }

    @Singleton
    @Provides
    fun provideArticleService(): ArticleService {
        return ArticleService.create()
    }
}