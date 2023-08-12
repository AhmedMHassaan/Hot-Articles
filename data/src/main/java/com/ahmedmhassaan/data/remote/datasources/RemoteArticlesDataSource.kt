package com.ahmedmhassaan.data.remote.datasources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.util.query
import com.ahmedmhassaan.data.model.Article
import com.ahmedmhassaan.data.remote.api.ArticleService
import javax.inject.Inject

class RemoteArticlesDataSource @Inject constructor(
    private val articleService: ArticleService,
    private val query: String
) {

    suspend fun search() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ArticlesPagingDataSource(service = articleService, query = query)
            },


            )
}