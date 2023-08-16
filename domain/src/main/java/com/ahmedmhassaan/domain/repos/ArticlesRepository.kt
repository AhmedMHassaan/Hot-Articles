package com.ahmedmhassaan.domain.repos

import androidx.paging.PagingData
import com.ahmedmhassaan.domain.models.DomainArticle
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {

    suspend fun searchForArticles(query: String): Flow<PagingData<DomainArticle>>

    suspend fun fetchTopHeadLines(): Flow<PagingData<DomainArticle>>
}