package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.database.ArticleDao
import com.example.myapplication.data.database.ArticleDatabase
import com.example.myapplication.data.database.NavigationDao
import com.example.myapplication.data.database.NavigationDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideNavigationDatabase(@ApplicationContext context: Context): NavigationDatabase {
        return NavigationDatabase.getInstance(context)
    }


    @Provides
    fun provideNavigationDao(database: NavigationDatabase): NavigationDao {
        return database.navigationDao()
    }

    @Singleton
    @Provides
    fun provideArticleDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return ArticleDatabase.getInstance(context)
    }


    @Provides
    fun provideArticleDao(database: ArticleDatabase): ArticleDao {
        return database.articleDao()
    }



}