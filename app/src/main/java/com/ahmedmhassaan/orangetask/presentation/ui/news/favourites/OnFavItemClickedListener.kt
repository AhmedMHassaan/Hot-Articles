package com.ahmedmhassaan.orangetask.presentation.ui.news.favourites

import com.ahmedmhassaan.domain.models.DomainArticle

interface OnFavItemClickedListener {
    fun showArticleDetails(article: DomainArticle)

    fun removeFromFav(article: DomainArticle)
}