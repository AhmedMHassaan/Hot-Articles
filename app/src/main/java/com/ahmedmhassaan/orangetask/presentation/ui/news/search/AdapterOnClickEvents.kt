package com.ahmedmhassaan.orangetask.presentation.ui.news.search

import com.ahmedmhassaan.domain.models.DomainArticle

interface AdapterOnClickEvents {

    fun showArticleDetails(article: DomainArticle)

    fun addToFavourite(article: DomainArticle)

    fun shareArticle(article: DomainArticle)
}