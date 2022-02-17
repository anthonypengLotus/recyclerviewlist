package com.example.myapplication.viewModel

import android.content.Context
import android.view.View
import androidx.lifecycle.*
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.fragment.RefreshTokenFragmentDirections
import com.example.myapplication.repository.RefreshRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RefreshViewModel @Inject constructor(
    private val repository: RefreshRepository
) : ViewModel() {

    suspend fun requestData(context: Context, owner: LifecycleOwner, view: View?) {
        refreshTokenObserver(context, owner, view)
    }

    private suspend fun refreshTokenObserver(
        context: Context, owner: LifecycleOwner, view: View?
    ) = Observer<String?> { result ->
        if (result != null) {
            viewModelScope.launch { categoryObserver(context, owner, view,result) }
        }
    }.apply {
        _loadingContent.value = context.getString(R.string.load_data)
        reFresh(context).observe(owner, this)
    }

    private suspend fun categoryObserver(
        context: Context, owner: LifecycleOwner, view: View?, token: String
    ) = Observer<String?> { result ->
        if (result != null) {
            val direction = RefreshTokenFragmentDirections.actionSplashFragmentToHomeFragment()
            view?.findNavController()?.navigate(direction)
        }
    }.apply {
        _loadingContent.value = context.getString(R.string.load_data)
        getCategoryList(context,token).observe(owner, this)
    }

    private suspend fun reFresh(context: Context): LiveData<String?> {
        return repository.getToken(context)
    }
    private suspend fun getCategoryList(context: Context, token: String): LiveData<String?> {
        return repository.getCategoryList(context, token)
    }

    private val _loadingContent = MutableLiveData<String>()
    val loadingContent: LiveData<String>
        get() = _loadingContent
}