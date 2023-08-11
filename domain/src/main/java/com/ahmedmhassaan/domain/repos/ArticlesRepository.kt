package com.ahmedmhassaan.domain.repos

import com.ahmedmhassaan.domain.models.DomainArticle

interface ArticlesRepository {

    suspend fun searchForArticles(
        query: String,
        pageNumber: Int,
        from: String,
        language: String
    ): List<DomainArticle>
}