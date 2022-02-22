package com.example.myapplication.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.ArticleService
import com.example.myapplication.api.ShopService
import com.example.myapplication.base.ShopApplication
import com.example.myapplication.data.database.*
import java.util.ArrayList

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesRepository @Inject constructor(private val navigationDao: NavigationDao,private val articlesDao: ArticleDao,private val serviceArticles: ArticleService,private val service: ShopService) {
    suspend fun getToken(): String? {
        val response = service.refresh("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoVHlwZSI6InVzZXJzIiwiZW1haWwiOiI5OTYyNjcwNzdAcXEuY29tIiwiaWQiOiI2MjFjMDEwZTc1ZDU0ZTY5YWYyYTQ3NWU0NGUwMTVkZCIsImtleV9pZCI6Ijc5OTBjMGE2MzU4ODQ4MzI5ODAzNzhlZmU3OTNlNDFlIiwiZXBvY2giOjE2NDQ0ODI0NTIsInBhcnRuZXJfbmFtZSI6ImFuZHJvaWQtYXBwIiwidHlwZSI6MCwiaWF0IjoxNjQ0NDgxODUyLCJleHAiOjE2NDk2NjU4NTJ9.qrjAZve690sib09BzdqcnQptkw7K_GmtP3DmBp8H7Uo")
        response.headers()
        return (response.headers()["x-amzn-Remapped-authorization"])
    }
    suspend fun getCategoryList(token: String): Boolean {

        return try {
            val response = service.navigation(token)
            response.d.article
            val articles: MutableList<ArticleEntity> = mutableListOf()
            val rawArticle = response.d.article
            rawArticle.mapIndexed { i, article ->
                articles.add(
                    ArticleEntity(
                        article.id, article.title, article.tag_indexed,
                        article.link, article.slug,
                        article.image, article.image_color,
                        article.web_view,
                        article.api
                    )
                )
            }

            val navigations: MutableList<NavigationEntity> = mutableListOf()
            val rawNavigation = response.d.navigation
            for (i in rawNavigation.indices) {
                navigations.add(
                    NavigationEntity(
                        rawNavigation[i].name,
                        rawNavigation[i].focusColor,
                        rawNavigation[i].api,
                        rawNavigation[i].key
                    )
                )
            }
            navigations.add(0, NavigationEntity("My Feed","ccc","ccc","ccc"))
            val databaseNavigation = NavigationDatabase.getInstance(ShopApplication.getContext())
            databaseNavigation.navigationDao().deleteList()
            databaseNavigation.navigationDao().insertList(navigations)

            val database = ArticleDatabase.getInstance(ShopApplication.getContext())
            database.articleDao().deleteList()
            database.articleDao().insertList(articles)
            return true
        } catch (e: Exception) {
            Log.e("Exception",e.message.toString())
            return false
        }
    }
    suspend fun getArticles(api: String) : ArrayList<ArticleEntity>{
            val response = serviceArticles.productList(api)
            val articles: ArrayList<ArticleEntity> = arrayListOf()
            val rawArticle = response.res.article
            rawArticle.mapIndexed { i, article ->
                articles.add(
                    ArticleEntity(
                        article.id, article.title, article.tag_indexed,
                        article.link, article.slug,
                        article.image, article.image_color,
                        article.web_view,
                        article.api
                    )
                )
            }
            return articles
    }
    fun getArticles() = articlesDao.getList()
    fun getPlayers() = navigationDao.getList()
}