package com.example.myapplication.viewModel

import androidx.lifecycle.*
import com.example.myapplication.adapter.ArticlesAdapter
import com.example.myapplication.data.database.ArticleEntity
import com.example.myapplication.data.database.NavigationEntity
import com.example.myapplication.repository.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {
    val token = MutableLiveData<String>()
    val categoryList = MutableLiveData<Boolean>()
    var splashContent = MutableLiveData<String>()
    var articles = MutableLiveData<List<ArticleEntity>>()
    var listFeed = articlesRepository.getArticles().asLiveData()
    var lastIns = 0

    val categoryResponseData: LiveData<List<NavigationEntity>> =
        articlesRepository.getCategory().asLiveData()

    fun requestData(api: String) {
        viewModelScope.launch {
            articles.value = articlesRepository.getArticles(api)
        }
    }

    fun reFresh() {
        splashContent.postValue("loading token")
        viewModelScope.launch {
            token.value = articlesRepository.getToken()
        }

    }

    fun getCategoryList(token: String) {
        splashContent.postValue("loading navigation")
        viewModelScope.launch {
            categoryList.value = articlesRepository.getCategoryList(token)
        }

    }
}



