package com.ahmedmhassaan.data.repository

import com.ahmedmhassaan.data.ext.toDomainArticle
import com.ahmedmhassaan.data.ext.toEntityArticle
import com.ahmedmhassaan.data.local.datasources.LocalArticlesDataSource
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.repos.LocalCacheArticlesRepository
import javax.inject.Inject

class LocalCacheRepositoryImpl @Inject constructor(
    private val cacheDataSource: LocalArticlesDataSource
) : LocalCacheArticlesRepository {
    override suspend fun loadCashedArticles(): List<DomainArticle> {
        return cacheDataSource.loadCachedArticles().map {
            it.toDomainArticle()
        }
    }

    override suspend fun cacheArticles(request: List<DomainArticle>) {
        return cacheDataSource.cacheArticles(request.map {
            it.toEntityArticle()
        })
    }
}