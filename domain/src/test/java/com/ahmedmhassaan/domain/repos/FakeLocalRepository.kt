package com.ahmedmhassaan.domain.repos

import com.ahmedmhassaan.domain.models.DomainArticle

class FakeLocalRepository : LocalCacheArticlesRepository {

    private val favList = mutableListOf<DomainArticle>()

    override suspend fun loadCashedArticles(): List<DomainArticle> {
        return favList
    }

    override suspend fun cacheArticles(request: List<DomainArticle>) {
        favList.addAll(request)
    }
}