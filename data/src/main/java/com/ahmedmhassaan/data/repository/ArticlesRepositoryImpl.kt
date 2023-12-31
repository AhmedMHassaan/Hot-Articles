package com.ahmedmhassaan.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.ahmedmhassaan.data.ext.toDomainArticle
import com.ahmedmhassaan.data.remote.datasources.RemoteArticlesDataSource
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.repos.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val articleDataSource: RemoteArticlesDataSource,
) : ArticlesRepository {
    override suspend fun searchForArticles(query: String): Flow<PagingData<DomainArticle>> {

        return articleDataSource.search(query).map { articlePagingData ->
            articlePagingData.map {
                it.toDomainArticle()
            }

        }
    }

    override suspend fun fetchTopHeadLines(): Flow<PagingData<DomainArticle>> {
        return articleDataSource.topHeadLines().map { articlePagingData ->
            articlePagingData.map {
                it.toDomainArticle()
            }

        }
    }
}