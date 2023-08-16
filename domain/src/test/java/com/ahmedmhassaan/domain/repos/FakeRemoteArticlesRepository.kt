package com.ahmedmhassaan.domain.repos

import androidx.paging.PagingData
import com.ahmedmhassaan.domain.models.DomainArticle
import kotlinx.coroutines.flow.Flow

class FakeRemoteArticlesRepository : ArticlesRepository {

    override suspend fun searchForArticles(query: String): Flow<PagingData<DomainArticle>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTopHeadLines(): Flow<PagingData<DomainArticle>> {
        TODO("Not yet implemented")
    }
}