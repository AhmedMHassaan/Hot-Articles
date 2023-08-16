package com.ahmedmhassaan.orangetask.presentation.ui.news.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.usecase.LoadFavouritesArticlesUseCase
import com.ahmedmhassaan.domain.usecase.RemoveArticleFromFavouriteUseCase
import com.ahmedmhassaan.orangetask.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val getFavouritesArticlesUseCase: LoadFavouritesArticlesUseCase,
    private val removeItemFromFavouritesArticleUseCase: RemoveArticleFromFavouriteUseCase
) : BaseViewModel() {


    private val _progress = MutableLiveData<Boolean>(true)
    val progress: LiveData<Boolean> = _progress


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    private val _favList = MutableLiveData<ArrayList<DomainArticle>>()
    val favList: LiveData<ArrayList<DomainArticle>> = _favList

    private val _removedFromFav = MutableLiveData<Boolean>()
    val removedFromFav: LiveData<Boolean> = _removedFromFav


    init {
        loadFavouritesItems()
    }

    private fun loadFavouritesItems() {
        getFavouritesArticlesUseCase.invoke(null).dataHandling(
            success = {
                _favList.postValue(ArrayList(it))
            },
            showLoading = {
                _progress.postValue(it)
            },
            showError = {
                _error.postValue(it.message.toString())
            }
        )
    }

    fun removeArticleFromFav(domainArticle: DomainArticle) {
        removeItemFromFavouritesArticleUseCase.invoke(domainArticle)
            .dataHandling(
                success = {
                    _removedFromFav.postValue(it)
                    if (it) {
                        loadFavouritesItems()
                    }
                },
                showError = {
                    _error.postValue(it.message.toString())
                },
                showLoading = {
                    _progress.postValue(it)
                }
            )
    }


}