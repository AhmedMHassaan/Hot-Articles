package com.ahmedmhassaan.orangetask.presentation.ui.news.search_fragmeent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.usecase.SearchForArticlesUseCase
import com.ahmedmhassaan.orangetask.presentation.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchForArticlesUseCase: SearchForArticlesUseCase
) : BaseViewModel() {

    private val _searchResult = MutableLiveData<PagingData<DomainArticle>>()
    val searchResultLiveData: LiveData<PagingData<DomainArticle>> = _searchResult


    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
//        search("")
    }

    fun search(query: String?) {
        query?.let {
            searchForArticlesUseCase.invoke(query).dataHandling(
                success = { res ->
                    res.onEach {
                        Log.d("APP_TAG", "SearchViewModel - search:  result is $it")
                        _searchResult.postValue(it)
                    }.cachedIn(viewModelScope)
                        .launchIn(viewModelScope)
                },
                showLoading = {
                    _progress.postValue(it)
                },
                showError = {
                    _error.postValue(it.message)
                }
            )

        }

    }
}