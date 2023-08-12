package com.ahmedmhassaan.domain.repos

import com.ahmedmhassaan.domain.models.DomainArticle

interface LocalCacheArticlesRepository {

    suspend fun loadCashedArticles(
        currentPage: Int,
        language: String
    ): List<DomainArticle>



    suspend fun cacheArticles(request: List<DomainArticle>)
}