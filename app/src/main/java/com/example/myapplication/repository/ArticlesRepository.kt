package com.example.myapplication.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.ArticleService
import com.example.myapplication.api.ShopService
import com.example.myapplication.data.database.ArticleDao
import com.example.myapplication.data.database.ArticleEntity

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesRepository @Inject constructor(private val playersDao: ArticleDao,private val service: ArticleService) {

    suspend fun getArticles(api: String): LiveData<List<ArticleEntity>> {
        val responseRe = MutableLiveData<List<ArticleEntity>>()
        return try {
            val response = service.productList(api)

            val articles: MutableList<ArticleEntity> = mutableListOf()
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

            responseRe.postValue(articles)
            responseRe
        } catch (e: Exception) {
            Log.e("Exception",e.toString())
            val articles: MutableList<ArticleEntity> = mutableListOf()
            responseRe.postValue(articles)
            responseRe
        }
    }
    fun getArticles() = playersDao.getList()
}