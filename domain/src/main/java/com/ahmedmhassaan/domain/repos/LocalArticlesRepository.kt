package com.ahmedmhassaan.domain.repos

import com.ahmedmhassaan.domain.models.DomainArticle

interface LocalArticlesRepository {

    suspend fun loadCashedArticles(
        currentPage: Int,
        pageSize: Int,
        language: String
    ): List<DomainArticle>

    suspend fun loadFavourites(): List<DomainArticle>

    suspend fun addArticleToFav(article: DomainArticle): Boolean

    suspend fun removeArticleFromFav(article: DomainArticle): Boolean
}