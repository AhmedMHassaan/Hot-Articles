package com.ahmedmhassaan.domain.repos

import androidx.paging.PagingData
import com.ahmedmhassaan.domain.models.DomainArticle
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {

    suspend fun searchForArticles(
    ): Flow<PagingData<DomainArticle>>
}