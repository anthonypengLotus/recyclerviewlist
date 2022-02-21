package com.example.myapplication.viewModel

import androidx.lifecycle.*
import com.example.myapplication.adapter.ArticlesAdapter
import com.example.myapplication.data.database.ArticleEntity
import com.example.myapplication.data.database.NavigationEntity
import com.example.myapplication.repository.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {

    var  articles = MutableLiveData<List<ArticleEntity>>()
    var listFeed = articlesRepository.getArticles().asLiveData()
    suspend fun requestData(api: String) {
            articles.value = articlesRepository.getArticles(api)
    }

    val categoryResponseData: LiveData<List<NavigationEntity>> = articlesRepository.getPlayers().asLiveData()

    suspend fun reFresh() {
        splashContent.postValue("loading token")
        token.value=articlesRepository.getToken()
    }
    suspend fun getCategoryList(token: String) {
        splashContent.postValue("loading navigation")
        categoryList.value = articlesRepository.getCategoryList(token)

    }

    val token = MutableLiveData<String>()
    val categoryList = MutableLiveData<Boolean>()

    var splashContent = MutableLiveData<String>()
}



