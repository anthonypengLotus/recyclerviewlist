package com.example.myapplication.viewModel

import androidx.lifecycle.*
import com.example.myapplication.adapter.ArticlesAdapter
import com.example.myapplication.data.database.ArticleEntity
import com.example.myapplication.repository.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {

    suspend fun requestData(owner: LifecycleOwner, playersAdapter: ArticlesAdapter, api: String) {
        if (api == "ccc") {
            playersObserver(owner, playersAdapter)
        } else {
            playersObserver(owner, playersAdapter, api)
        }

    }

    private fun playersObserver(
        owner: LifecycleOwner, playersAdapter: ArticlesAdapter
    ) = Observer<List<ArticleEntity>> { players ->
        playersAdapter.submitList(players)
    }.apply {
        val players: LiveData<List<ArticleEntity>> = articlesRepository.getArticles().asLiveData()
        players.observe(owner, this)
    }

    private suspend fun playersObserver(
        owner: LifecycleOwner, playersAdapter: ArticlesAdapter, api: String
    ) = Observer<List<ArticleEntity>> { players ->
        playersAdapter.submitList(players)
    }.apply {
//        val players: LiveData<List<ArticleEntity>> = articlesRepository.getArticles().asLiveData()
        getOtherArticles(api).observe(owner, this)
    }

    private suspend fun getOtherArticles(api: String): LiveData<List<ArticleEntity>> {
        return articlesRepository.getArticles(api)
    }
}


