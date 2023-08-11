package com.ahmedmhassaan.domain.repos

import com.ahmedmhassaan.domain.models.DomainArticle

interface ArticlesRepository {

    suspend fun searchForArticles(
        query: String,
        pageSize: Int,
        pageNumber: Int,
        from: String,
        sortBy: String,
        language: String
    ): List<DomainArticle>
}